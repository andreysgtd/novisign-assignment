package com.novisign.assignment.services;

import com.novisign.assignment.exceptions.SlideshowImageNotFoundException;
import com.novisign.assignment.exceptions.SlideshowNotFoundException;
import com.novisign.assignment.models.*;
import com.novisign.assignment.repositories.SlideshowImageProofOfPlayRepository;
import com.novisign.assignment.repositories.SlideshowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class SlideshowService {

    @Autowired
    private SlideshowRepository slideshowRepository;
    @Autowired
    private SlideshowImageProofOfPlayRepository slideshowImageProofOfPlayRepository;

    public Slideshow createSlideshow(CreateSlideshowRequest createSlideshowRequest) {
        Slideshow slideshow = new Slideshow();
        slideshow.setName(createSlideshowRequest.getName());
        List<SlideshowImage> slideshowImages = new ArrayList<>();
        for (CreateSlideshowRequest.CreateSlideShowRequestImage createSlideShowRequestImage :
                createSlideshowRequest.getImages()) {
            SlideshowImage slideshowImage = new SlideshowImage();
            Image image = new Image();
            image.setId(createSlideShowRequestImage.getId());
            slideshowImage.setImage(image);
            slideshowImage.setDurationMillis(createSlideShowRequestImage.getDurationMillis());
            slideshowImages.add(slideshowImage);
        }
        slideshow.setImages(slideshowImages);
        return slideshowRepository.save(slideshow);
    }

    public void deleteSlideshow(Long id) {
        if (!slideshowRepository.existsById(id)) {
            throw new SlideshowNotFoundException(
                    String.format("Slideshow with id %s was not found.", id)
            );
        }
        slideshowRepository.deleteById(id);
    }

    public Image[] getSlideshowImagesOBImageCreateDate(Long slideshowId) {
        var slideshow = slideshowRepository.findById(slideshowId);
        if (slideshow.isEmpty()) {
            throw new SlideshowNotFoundException(
                    String.format("Slideshow with id %s was not found.", slideshowId)
            );
        }

        Comparator<? super Image> comparator = (Comparator<Image>) (
                a1, a2) -> a1.getCreateDate().compareTo(a2.getCreateDate()) * (-1);
        var imagesList = slideshow.get().getImages().stream().map(SlideshowImage::getImage).sorted(comparator).toList();
        return imagesList.toArray(new Image[0]);
    }

    public void recordProofOfPlayEventInSlideshow(Long slideshowId, Long imageId) {
        var slideshow = slideshowRepository.findById(slideshowId);
        if (slideshow.isEmpty()) {
            throw new SlideshowNotFoundException(
                    String.format("Slideshow with id %s was not found.", slideshowId)
            );
        }
        var slideshowImageFound = slideshow.get().getImages().stream().anyMatch(
                (a) -> Objects.equals(a.getImage().getId(), imageId)
        );
        if (!slideshowImageFound) {
            throw new SlideshowImageNotFoundException(
                    String.format("Image with id %s is not part of slideshow with id %s.", imageId, slideshowId)
            );
        }

        var slideshowImageProofOfPlay = new SlideshowImageProofOfPlay();
        var image = new Image();
        image.setId(imageId);
        slideshowImageProofOfPlay.setSlideshow(slideshow.get());
        slideshowImageProofOfPlay.setImage(image);
        slideshowImageProofOfPlayRepository.save(slideshowImageProofOfPlay);
    }
}
