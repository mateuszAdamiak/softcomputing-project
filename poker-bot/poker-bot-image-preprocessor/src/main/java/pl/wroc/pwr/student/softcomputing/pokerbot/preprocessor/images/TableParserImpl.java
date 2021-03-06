package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.*;
import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSplitter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;

public class TableParserImpl implements TableParser {
	private BufferedImage table = null;
	private ImageLoader imageLoader = new ImageFromFileLoader();
	private ImageSplitter imageSplitter = new ImageFromBufferedImageSplitter();

    private final int X_OFFSET = 1;
    private final int Y_OFFSET = 3;

	@Override
	public void loadTable(String tableImageFile) {
		table = imageLoader.load(tableImageFile);
    }

	@Override
	public void loadTable(BufferedImage image) {
		table = image;
	}

	@Override
	public BufferedImage parseFoldButton() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 465, 555, 515 - 465, 580 - 555);
	}

	@Override
	public BufferedImage parseFirstCard() {
        int xCardPosition = calculateXCardPosition();
        int yCardPosition = calculateYCardPosition();
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, xCardPosition, yCardPosition, 18, 19);
	}

	@Override
	public BufferedImage parseSecondCard() {
        int xCardPosition = calculateXCardPosition();
        int yCardPosition = calculateYCardPosition();
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, xCardPosition+57, yCardPosition, 18, 19);
	}

	@Override
	public BufferedImage parseFirstSuit() {
        int xCardPosition = calculateXCardPosition();
        int yCardPosition = calculateYCardPosition();
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, xCardPosition+1, yCardPosition+22, 17, 18);
	}

	@Override
	public BufferedImage parseSecondSuit() {
        int xCardPosition = calculateXCardPosition();
        int yCardPosition = calculateYCardPosition();
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, xCardPosition+58, yCardPosition+22, 17, 18);
	}

	@Override
	public BufferedImage parseDealerButton() {
		throwExceptionIfTableNotLoaded();
		BufferedImage firstPosition = imageSplitter.crop(table, 503, 373, 19,
				17);
		BufferedImage secondPosition = imageSplitter.crop(table, 216, 334, 19,
				17);
		BufferedImage thirdPosition = imageSplitter.crop(table, 188, 189, 19,
				17);
		BufferedImage fourthPosition = imageSplitter.crop(table, 363, 131, 19,
				17);
		BufferedImage fifthPosition = imageSplitter.crop(table, 677, 194, 19,
				17);
		BufferedImage sixthPosition = imageSplitter.crop(table, 640, 334, 19,
				17);
		BufferedImage combined = new BufferedImage(19 * 6, 17, table.getType());

		Graphics g = combined.getGraphics();
		g.drawImage(firstPosition, 19 * 0, 0, null);
		g.drawImage(secondPosition, 19 * 1, 0, null);
		g.drawImage(thirdPosition, 19 * 2, 0, null);
		g.drawImage(fourthPosition, 19 * 3, 0, null);
		g.drawImage(fifthPosition, 19 * 4, 0, null);
		g.drawImage(sixthPosition, 19 * 5, 0, null);

		g.dispose();
		return combined;
	}

	@Override
	public BufferedImage parseOpponentChips(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		int x=0, y=0;
		switch(opponentNumber){
		case 1:
			x=49;
			y=353;
			break;
		case 2:
			x=49;
			y=164;
			break;
		case 3:
			x=375;
			y=91;
			break;
		case 4:
			x=731;
			y=164;
			break;
		case 5:
			x=731;
			y=353;
			break;
		}
		return imageSplitter.crop(table, x, y, 94, 18);
	}

	@Override
	public BufferedImage parseOpponentTableChips(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		int x=0, y=0;
		switch(opponentNumber){
		case 1:
			x=212;
			y=309;
			break;
		case 2:
			x=237;
			y=173;
			break;
		case 3:
			x=416;
			y=137;
			break;
		case 4:
			x=495;
			y=174;
			break;
		case 5:
			x=510;
			y=310;
			break;
		}
		return imageSplitter.crop(table, x, y, 150, 14);
	}

	@Override
	public BufferedImage parsePlayerChips() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 403, 458, 94, 18);
	}

	@Override
	public BufferedImage parsePlayerTableChips() {
		throwExceptionIfTableNotLoaded();
		return imageSplitter.crop(table, 327, 342, 150, 14);
	}

	@Override
	public BufferedImage parseOpponentCards(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		int x=0, y=0;
		switch(opponentNumber){
		case 1:
			x=60;
			y=301;
			break;
		case 2:
			x=60;
			y=112;
			break;
		case 3:
			x=386;
			y=40;
			break;
		case 4:
			x=714;
			y=112;
			break;
		case 5:
			x=714;
			y=301;
			break;
		}
		return imageSplitter.crop(table, x, y, 46, 22);
	}

	@Override
	public BufferedImage parseOpponentBorder(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		int x=0, y=0;
		switch(opponentNumber){
		case 1:
			x=55;
			y=324;
			break;
		case 2:
			x=55;
			y=134;
			break;
		case 3:
			x=381;
			y=62;
			break;
		case 4:
			x=739;
			y=134;
			break;
		case 5:
			x=729;
			y=324;
			break;
		}
		return imageSplitter.crop(table, x, y, 10, 2);
	}

	@Override
	public BufferedImage parseOpponentAppereanceAtTable(int opponentNumber) {
		throwExceptionIfTableNotLoaded();
		throwExceptionIfInvalidOpponentNumberIsGiven(opponentNumber);
		int x=0, y=0;
		switch(opponentNumber){
		case 1:
			x=50;
			y=348;
			break;
		case 2:
			x=50;
			y=159;
			break;
		case 3:
			x=375;
			y=87;
			break;
		case 4:
			x=747;
			y=159;
			break;
		case 5:
			x=747;
			y=348;
			break;
		}
		return imageSplitter.crop(table, x, y, 75, 3);
	}

    @Override
    public BufferedImage parseTableCards() {
        throwExceptionIfTableNotLoaded();
        return imageSplitter.crop(table, 283, 208, 271, 22);
    }

    private void throwExceptionIfTableNotLoaded() {
		if (table == null)
			throw new NoTableLoadedException();
	}

	private void throwExceptionIfInvalidOpponentNumberIsGiven(int opponentNumber) {
		if (opponentNumber < 1 || opponentNumber > 5)
			throw new InvalidOpponentNumberException();
	}

    private int calculateYCardPosition(){
        for(int i=375; i<=400; i++){
            Color c = new Color(table.getRGB(400,i));
            if(c.getRed()>250&&c.getGreen()>250&&c.getBlue()>250)return i+Y_OFFSET;
        }
        throw new CardNotFoundException();
    }

    private int calculateXCardPosition(){
        for(int i=375; i<=400; i++){
            Color c = new Color(table.getRGB(i,400));
            if(c.getRed()>250&&c.getGreen()>250&&c.getBlue()>250)return i+X_OFFSET;
        }
        throw new CardNotFoundException();
    }

	private class NoTableLoadedException extends RuntimeException {
		private static final long serialVersionUID = -2155329090680026978L;

	}

    private class InvalidOpponentNumberException extends RuntimeException {
        private static final long serialVersionUID = -8384037090000641476L;

    }

    private class CardNotFoundException extends RuntimeException {
        private static final long serialVersionUID = -8384037090000641476L;

    }

}
