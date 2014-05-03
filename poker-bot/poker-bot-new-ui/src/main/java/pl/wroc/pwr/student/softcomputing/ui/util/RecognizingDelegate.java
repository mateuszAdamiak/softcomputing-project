package pl.wroc.pwr.student.softcomputing.ui.util;

import pl.wroc.pwr.student.softcomputing.teacher.api.TeacherFacade;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

import java.io.File;
import java.util.List;

/**
 * Created by RaV on 17.01.14.
 */
public class RecognizingDelegate {
    public static Table recognize(File selectedFile, FileHolder figuresDatasource, FileHolder suitsDatasource, FileHolder dealerDatasource) {

        TeacherFacade facade = TeacherFacade.getInstance();

        String figureNNFile = figuresDatasource.getFile().toString();
        double scale = TeachingParams.getScaleFromFilename(figureNNFile);
        boolean bnw = TeachingParams.getBNWFromFilename(figureNNFile);
        boolean grayscale = TeachingParams.getGrayscaleFromFilename(figureNNFile);
        ImageConfig figureImageConfig = new TrainingImageConfig(scale, bnw, grayscale);


        String suitNNFile = suitsDatasource.getFile().toString();
        scale = TeachingParams.getScaleFromFilename(suitNNFile);
        bnw = TeachingParams.getBNWFromFilename(suitNNFile);
        grayscale = TeachingParams.getGrayscaleFromFilename(suitNNFile);
        ImageConfig suitImageConfig = new TrainingImageConfig(scale, bnw, grayscale);


        String dealerNNFile = dealerDatasource.getFile().toString();
        scale = TeachingParams.getScaleFromFilename(dealerNNFile);
        bnw = TeachingParams.getBNWFromFilename(dealerNNFile);
        grayscale = TeachingParams.getGrayscaleFromFilename(dealerNNFile);
        ImageConfig dealerImageConfig = new TrainingImageConfig(scale, bnw, grayscale);


        Table table = facade.recognizeTable(selectedFile, figureNNFile, figureImageConfig, suitNNFile, suitImageConfig, dealerNNFile, dealerImageConfig);
        System.out.println(table.report());

        return table;
    }

	public static String recognizeAll(List<File> allFiles,
			FileHolder figuresDatasource, FileHolder suitsDatasource,
			FileHolder dealerDatasource) {
		StringBuilder sb = new StringBuilder();
		for (File file : allFiles) {
			sb.append(recognize(file, figuresDatasource, suitsDatasource, dealerDatasource));
		}
		return sb.toString();
	}
}
