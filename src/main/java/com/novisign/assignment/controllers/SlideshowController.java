package com.novisign.assignment.controllers;

import com.novisign.assignment.exceptions.SlideshowImageNotFoundException;
import com.novisign.assignment.exceptions.SlideshowNotFoundException;
import com.novisign.assignment.models.CreateSlideshowRequest;
import com.novisign.assignment.models.Image;
import com.novisign.assignment.models.Slideshow;
import com.novisign.assignment.services.SlideshowService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("")
public class SlideshowController {

    @Autowired
    private SlideshowService slideshowService;

    @PostMapping("/addSlideshow")
    public Slideshow createSlideshow(@RequestBody @Valid CreateSlideshowRequest createSlideshowRequest) {
        return slideshowService.createSlideshow(createSlideshowRequest);
    }

    @DeleteMapping("/deleteSlideshow/{id}")
    public void deleteSlideshow(@PathVariable Long id) {
        try {
            slideshowService.deleteSlideshow(id);
        } catch (SlideshowNotFoundException ex1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex1.getMessage(), ex1);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "", e);
        }
    }

    @GetMapping("/slideshow/{id}/slideshowOrder")
    public Image[] getOrderedSlideshowImages(@PathVariable Long id) {
        try {
            return slideshowService.getSlideshowImagesOBImageCreateDate(id);
        } catch (SlideshowNotFoundException ex1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex1.getMessage(), ex1);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "", e);
        }
    }

    @PostMapping("/slideshow/{id}/proofOfPlay/{imageId}")
    public void recordProofOfPlayEventInSlideshow(
            @PathVariable Long id, @PathVariable Long imageId) {
        try {
            slideshowService.recordProofOfPlayEventInSlideshow(id, imageId);
        } catch (SlideshowNotFoundException | SlideshowImageNotFoundException ex1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex1.getMessage(), ex1);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "", e);
        }
    }
}
