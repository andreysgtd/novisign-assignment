package com.novisign.assignment.services;

import com.novisign.assignment.models.Image;
import com.novisign.assignment.models.Slideshow;
import com.novisign.assignment.models.SlideshowImage;
import com.novisign.assignment.repositories.SlideshowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SlideshowServiceUnitTests {

    @Mock
    private SlideshowRepository slideshowRepository;

    @InjectMocks
    private SlideshowService slideshowService;

    @Test
    public void getSlideshowImagesOBImageCreateDateTest() {

        // Preconditions

        Image image1 = new Image();
        image1.setId(1L);
        image1.setCreateDate(new Date(1));

        Image image2 = new Image();
        image2.setId(2L);
        image2.setCreateDate(new Date(2));

        SlideshowImage slideshowImage1 = new SlideshowImage();
        slideshowImage1.setImage(image1);

        SlideshowImage slideshowImage2 = new SlideshowImage();
        slideshowImage2.setImage(image2);

        Slideshow slideshow1 = new Slideshow();
        slideshow1.setId(11L);
        ArrayList<SlideshowImage> slideshowImages1 = new ArrayList<>();
        slideshowImages1.add(slideshowImage1);
        slideshowImages1.add(slideshowImage2);
        slideshow1.setImages(slideshowImages1);

        given(slideshowRepository.findById(slideshow1.getId())).willReturn(Optional.of(slideshow1));

        // Action

        Image[] actionResult = slideshowService.getSlideshowImagesOBImageCreateDate(slideshow1.getId());

        // Verification

        assertThat(actionResult.length).isEqualTo(2);
        assertThat(actionResult[0].getId()).isEqualTo(image2.getId());
        assertThat(actionResult[1].getId()).isEqualTo(image1.getId());
    }
}
