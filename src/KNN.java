


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * Knn algorithm implementation
 */
public class KNN {

	// created lists for storing training and testing datasets label and features.

	private List<double[]> trainDistance = new ArrayList<>();
	private List<String> trainlabel = new ArrayList<>();

	private List<double[]> testDistance = new ArrayList<>();
	 List<String> testlabel = new ArrayList<>();
	 List<String> original = new ArrayList<>();
	 List<String> Result = new ArrayList<>();

	/*
	 * sc object for getting user input
	 */

	Scanner sc = new Scanner(System.in);
	int knn_value = 7;
	//int DistanceMetricsSelction = 0;
    int totalNumberOfLabel = 0;

	/*
	 * loading training data and extracting features and label for training dataset
	 */
	void train() {

		String content=new String();
		try {
			File file=new File("train.txt");
			Scanner scan=new Scanner(file);
			while(scan.hasNextLine())
			 {
				content=scan.nextLine();
				String[] split = content.split(",");
				double[] distance = new double[split.length - 1];
				
				for (int i = 0; i < split.length - 1; i++)
					{distance[i] = Double.parseDouble(split[i]);
			//	System.out.println(distance[i]);
					}
				trainDistance.add(distance);
				trainlabel.add(split[distance.length]);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * loading testing data and extracting features and label for training dataset
	 * 
	 */
	void test()
	{

		String content=new String();
		try {
		File file=new File("teast.txt");
		Scanner scan=new Scanner(file);
		while(scan.hasNextLine()) {
			content=scan.nextLine();
				String[] split = content.split(",");
				double[] feature = new double[split.length - 1];
				for (int i = 0; i < split.length - 1; i++)
					feature[i] = Double.parseDouble(split[i]);
				testDistance.add(feature);
				testlabel.add(split[feature.length]);
				// writing original label for test data to file and counting number of label.
				original.add(split[feature.length]);
				totalNumberOfLabel++;

			}
		scan.close();
		}

		catch (FileNotFoundException e) {
			// TODO Auto catch block
			e.printStackTrace();
		}
}



	
	void euclideanDistance() throws FileNotFoundException {

		for (int i=0;i<testDistance.size();i++) {
			double testD[] = testDistance.get(i);
			
			ArrayList<DistanceLabel> DL = new ArrayList<>();
			for (int j=0;j<trainDistance.size();j++) {
				
				double trainD[] = trainDistance.get(j);
				double distance = getEuclideanDistance(trainD, testD);
//System.out.println("dist="+distance);
				String label = trainlabel.get(j);
				DistanceLabel DfObject = new DistanceLabel(distance, label);
				DL.add(DfObject);
				Collections.sort(DL);
				
			}

			int setsosa = 0;
		    int versicolor = 0;
		    int virginica = 0;

		   // int flag = 0;

			for(int n=0;n< knn_value ;n++) {
				DistanceLabel s = DL.get(n);
				String s1 = s.lable;
				if (s1.equals("Iris-setosa"))
					setsosa++;
				else if (s1.equals("Iris-versicolor"))
					versicolor++;
				else if (s1.equals("Iris-virginica"))
					virginica++;
		
			}

			if (setsosa > versicolor && setsosa > virginica) {
				Result.add("Iris-setosa");

			} else if (versicolor > setsosa && versicolor > virginica) {
				Result.add("Iris-versicolor");
			}

			else if (virginica > setsosa && virginica > versicolor) {
				
				Result.add("Iris-virginica");
			}
		}
		
	}

	
	public double getEuclideanDistance( double[] train,  double[] test) {
        double sum = 0;
        for (int i = 0; i < train.length; i++)
        { 
        	//System.out.println(train[i]+" "+test[i]+"  i="+i);
            sum += Math.pow(train[i] - test[i], 2);
        }return Math.sqrt(sum);
    }
	
	
	
	void getAccuracy() throws IOException {
		int count = 0;
		
        
		for (int i=0;i<original.size();i++ ) {
			//System.out.println(Result.get(i)+"===="+original.get(i));
			if (Result.get(i).equals(original.get(i))) {

				count++;
			

		}

		
	}

		System.out.println("Accuracy is: " + ( (((float) count / totalNumberOfLabel) * 100)) + "%");


}
}