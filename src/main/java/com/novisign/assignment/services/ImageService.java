package com.novisign.assignment.services;

import com.novisign.assignment.exceptions.ImageNotFoundException;
import com.novisign.assignment.models.Image;
import com.novisign.assignment.models.Slideshow;
import com.novisign.assignment.repositories.ImageRepository;
import com.novisign.assignment.repositories.SlideshowRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SlideshowRepository slideshowRepository;

    public Image createImage(Image image) {
        image.setCreateDate(new Date());
        return imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        if (!imageRepository.existsById(id)) {
            throw new ImageNotFoundException(String.format(
                    "Image with id %s was not found.", id
            ));
        }
        imageRepository.deleteById(id);
    }

    public Pair<List<Image>, Set<Slideshow>> getImagesAndSlideshowsByKeywordsAndDuration(
            String[] keywords, int minDurationMillis, int maxDurationMillis) {

        // TODO: handle the duality of duration between Image and SlideshowImage

        // select images that comply with the search params
        var selectedImages = imageRepository.findAll().stream().filter((a) ->
                Arrays.stream(keywords).anyMatch((b) -> a.getUrl().contains(b)) &&
                a.getDurationMillis() >= minDurationMillis &&
                a.getDurationMillis() <= maxDurationMillis
        ).toList();

        // select slideshows that reference the selected images and
        // comply with duration params on a slideshow image basis
        var selectedSlideshows = new HashSet<Slideshow>();
        var allSlideshows = slideshowRepository.findAll();
        selectedImages.forEach((a) -> {
            allSlideshows.forEach((b) -> {
                if (b.getImages().stream().anyMatch((c) ->
                        Objects.equals(c.getImage().getId(), a.getId()) &&
                        (c.getDurationMillis() >= minDurationMillis) &&
                        (c.getDurationMillis() <= maxDurationMillis)
                )) {
                    selectedSlideshows.add(b);
                }
            });
        });

        return Pair.of(selectedImages, selectedSlideshows);
    }
}
