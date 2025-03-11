package com.novisign.assignment.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.*;

public class ImageValidator implements ConstraintValidator<ImageValidation, String> {
    @Override
    public void initialize(ImageValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Image image;
        try {
            image = ImageIO.read(new URI(s).toURL());
            return image != null;
        } catch (URISyntaxException | IOException | IllegalArgumentException e) {
            return false;
        }
    }
}
