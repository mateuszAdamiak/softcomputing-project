package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSplitter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;

public class TableParserImpl implements TableParser {
	private BufferedImage table = null;
	private ImageLoader imageLoader = new ImageFromFileLoader();
	private ImageSplitter imageSplitter = new ImageFromBufferedImageSplitter();

	@Override
	public void loadTable(String tableImageFile) {
		table = imageLoader.load(tableImageFile);
	}

	@Override
	public BufferedImage parseFoldButton() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 465, 555, 515-465, 580-555);
	}

	@Override
	public BufferedImage parseFirstCard() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 380, 386, 18, 19);
	}

	@Override
	public BufferedImage parseSecondCard() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 437, 386, 18, 19);
	}

	@Override
	public BufferedImage parseFirstSuit() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 381, 408, 17, 18);
	}

	@Override
	public BufferedImage parseSecondSuit() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 438, 408, 17, 18);
	}

	@Override
	public BufferedImage parseDealerButton() {
		throwExceptionIfTableNotLoaded();
		BufferedImage firstPosition = imageSplitter.crop(table, 503, 373, 19, 17);
		BufferedImage secondPosition = imageSplitter.crop(table, 216, 334, 19, 17);
		BufferedImage thirdPosition = imageSplitter.crop(table, 188, 189, 19, 17);
		BufferedImage fourthPosition = imageSplitter.crop(table, 363, 131, 19, 17);
		BufferedImage fifthPosition = imageSplitter.crop(table, 677, 194, 19, 17);
		BufferedImage sixthPosition = imageSplitter.crop(table, 640, 334, 19, 17);
		BufferedImage combined = new BufferedImage(19*6, 17, table.getType());
		
		Graphics g = combined.getGraphics();
		g.drawImage(firstPosition, 19*0, 0, null);
		g.drawImage(secondPosition, 19*1, 0, null);
		g.drawImage(thirdPosition, 19*2, 0, null);
		g.drawImage(fourthPosition, 19*3, 0, null);
		g.drawImage(fifthPosition, 19*4, 0, null);
		g.drawImage(sixthPosition, 19*5, 0, null);
		
		g.dispose();
		return combined;
	}

	@Override
	public BufferedImage parseOpponentChips(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		return null;
	}

	@Override
	public BufferedImage parseOpponentTableChips(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		return null;
	}

	@Override
	public BufferedImage parsePlayerChips() {
		throwExceptionIfTableNotLoaded();
		return null;
	}

	@Override
	public BufferedImage parsePlayerTableChips() {
		throwExceptionIfTableNotLoaded();
		return null;
	}

	@Override
	public BufferedImage parseOpponentCards(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		return null;
	}

	@Override
	public BufferedImage parseOpponentBorder(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		return null;
	}

	@Override
	public BufferedImage parseOpponentAppereanceAtTable(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		return null;
	}
	
	private void throwExceptionIfTableNotLoaded(){
		if(table==null)throw new NoTableLoadedException();
	}
	
	private void throwExceptionIfInvalidOpponentNumberIsGiven(int opponentNumber){
		if(opponentNumber<1||opponentNumber>5)throw new InvalidOpponentNumberException();
	}
	
	private class NoTableLoadedException extends RuntimeException{
		private static final long serialVersionUID = -2155329090680026978L;
		
	}
	
	private class InvalidOpponentNumberException extends RuntimeException{
		private static final long serialVersionUID = -8384037090000641476L;
		
	}

}
