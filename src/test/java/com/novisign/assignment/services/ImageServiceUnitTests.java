package com.novisign.assignment.services;

import com.novisign.assignment.models.Image;
import com.novisign.assignment.models.Slideshow;
import com.novisign.assignment.models.SlideshowImage;
import com.novisign.assignment.repositories.ImageRepository;
import com.novisign.assignment.repositories.SlideshowRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ImageServiceUnitTests {

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private SlideshowRepository slideshowRepository;

    @InjectMocks
    private ImageService imageService;

    @Test
    public void getImagesAndSlideshowsByKeywordsAndDurationTest() {

        // Preconditions

        Image image1 = new Image();
        image1.setId(1L);
        image1.setUrl("www.example.com/abc.jpg");
        image1.setDurationMillis(3000);

        Image image2 = new Image();
        image2.setId(2L);
        image2.setUrl("www.example.com/cba.jpg");
        image2.setDurationMillis(4000);

        Image image3 = new Image();
        image3.setId(3L);
        image3.setUrl("www.example.com/def.jpg");
        image3.setDurationMillis(2000);

        SlideshowImage slideshowImage1 = new SlideshowImage();
        slideshowImage1.setImage(image1);
        slideshowImage1.setDurationMillis(4000);

        SlideshowImage slideshowImage2 = new SlideshowImage();
        slideshowImage2.setImage(image2);
        slideshowImage2.setDurationMillis(4000);

        SlideshowImage slideshowImage3 = new SlideshowImage();
        slideshowImage3.setImage(image3);
        slideshowImage3.setDurationMillis(4000);

        Slideshow slideshow1 = new Slideshow();
        slideshow1.setId(11L);
        ArrayList<SlideshowImage> slideshowImages1 = new ArrayList<>();
        slideshowImages1.add(slideshowImage1);
        slideshowImages1.add(slideshowImage2);
        slideshow1.setImages(slideshowImages1);

        Slideshow slideshow2 = new Slideshow();
        slideshow2.setId(12L);
        ArrayList<SlideshowImage> slideshowImages2 = new ArrayList<>();
        slideshowImages2.add(slideshowImage3);
        slideshow2.setImages(slideshowImages2);

        given(imageRepository.findAll()).willReturn(List.of(image1, image2, image3));
        given(slideshowRepository.findAll()).willReturn(List.of(slideshow1, slideshow2));

        // Action

        String[] keywords = new String[1];
        keywords[0] = "ba";
        Pair<List<Image>, Set<Slideshow>> actionResult = imageService.getImagesAndSlideshowsByKeywordsAndDuration(
                keywords, 3500, 4500);

        // Verification

        assertThat(actionResult.getLeft().size()).isEqualTo(1);
        assertThat(actionResult.getRight().size()).isEqualTo(1);
        assertThat(actionResult.getLeft().getFirst().getId()).isEqualTo(2L);
        assertThat(actionResult.getRight().iterator().next().getId()).isEqualTo(11L);
    }
}
