package pl.wroc.pwr.student.softcomputing.teacher.recognition.suits;

import java.awt.image.BufferedImage;
import java.io.File;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

public class SuitImagesBuilder implements ImagesBuilder<BufferedImage, File> {
	private final TableParser tableParser;
	private final ImageProcessor imageProcessor;

	public SuitImagesBuilder(TableParser tableParser,
			ImageProcessor imageProcessor) {
		this.tableParser = tableParser;
		this.imageProcessor = imageProcessor;
	}

	@Override
	public Images<BufferedImage> buildFrom(File file, ImageConfig imageConfig) {
		Images<BufferedImage> images = new TrainingImages();
		tableParser.loadTable(file.getAbsolutePath());
		
		BufferedImage firstCard = tableParser.parseFirstSuit();
		firstCard = processImage(imageConfig, firstCard);
		TrainingImage firstCardImage = new TrainingImage(firstCard, getFirstCardName(file.getAbsolutePath()));
		images.add(firstCardImage);
		
		BufferedImage secondCard = tableParser.parseSecondSuit();
		secondCard = processImage(imageConfig, secondCard);
		TrainingImage secondCardImage = new TrainingImage(secondCard, getSecondCardName(file.getAbsolutePath()));
		images.add(secondCardImage);
		
		return images;
	}

	private BufferedImage processImage(ImageConfig imageConfig,
			BufferedImage image) {
		if(imageConfig.getScale() < 1.0)
			image = imageProcessor.scale(image, imageConfig.getScale());
		if(imageConfig.isBlackAndWhite()) 
			image = imageProcessor.convertToBlackAndWhite(image);
		if(imageConfig.isGrayscale())
			image = imageProcessor.convertToGrayscale(image);
		return image;
	}

	private String getFirstCardName(String name) {
		return name.substring(name.lastIndexOf(File.separator)).substring(2, 3);
	}

	private String getSecondCardName(String name) {
		return name.substring(name.lastIndexOf(File.separator)).substring(4, 5);
	}

}
