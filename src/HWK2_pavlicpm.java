/* Name: Phillip Pavlich
 * MacID: pavlicpm
 * Student Number: 1414960
 * Description: This is our second homework assignment which is intended to
 * take a set of matrices, multiply them together and compute the inverse of that answer.
 * It also deals with a matrix multiplication error or a non invertible matrix.
 */
public class HWK2_pavlicpm {//opening class name
	public static void main(String[] args) {//initializing function note***** I used a 1-D array to compute the multiplying matrices, didn't know of 2D matrices till after. Makes it harder to follow but it works.
		//after this function, I made smaller functions that are much easier to follow my process of though when creating this
		//if given only 1 matrix, my code will skip the multiplying part, and just invert that matrix
		int N=Integer.parseInt(args[0]);//taking argument 0 as an integer for number of matrices
		int[] matrixsize; //creating an array named matrixsize
		matrixsize=new int[N*2]; //initializing the size of the array
		int argumentnumber=0; //initializing an accumulator for argument number
		for(int i=0;i<(N*2);i++){ //a for loop to gather all the matrix sizes
			matrixsize[i]=Integer.parseInt(args[i+1]); //appending the matrix sizes into a variable
			argumentnumber++; //incrementing my accumulator for argumentnumber
		}//closing for loop
		
		if (N==1){//checking to see if only 1 matrix was given
			int[][] answer1; //initializing an array of matrix elements
			answer1=new int[matrixsize[0]][matrixsize[1]];// creating the size of the array
			for(int f=0;f<matrixsize[0];f++){//for loop to iterate through
				for(int i=0;i<matrixsize[0];i++){//for loop to iterate through
					answer1[f][i]=Integer.parseInt(args[argumentnumber+1]);//appending each argument that is a matrix element to the array
					argumentnumber++;//incrementing my accumulator argumentnumber
				}//closing for loop
			}//closing for loop
			
			if ((checkinginvertibility(answer1)==true) && (matrixsize[0]==matrixsize[(N*2)-1])){//checking if matrix is invertible
				Inversion(answer1);//calls function Inverse to compute the inverse of answer1
			}//closing if statement
			
			else{//if matrix is not invertible
				System.out.println("The matrix is not invertible");//print matrix not invertible
			}//closing else statement
		}//closing if statement
		
		else{//if given more than 1 matrix
			boolean multiply=true;// setting a boolean multiply to true so the while loop will run
			for(int J=0;J<((N-1)*2);J=J+2){//check for multiplication error
				if (matrixsize[1+J]!=matrixsize[2+J]){//checks if the columns of first matrix equals rows of second matrix
					multiply=false;//changes multiply to false so while loop won't run again
					System.out.println("Multiplication error");//print out multiplication error
				}//closing if statement
			}//closing for loop
			
			if (multiply==true){//check if multiplication is valid
				int numofelements=0; //initializing an accumulator for number of elements needed
				for(int g=0;g<(N*2);g=g+2){ //for loop to go through the indices of matrixsize
					numofelements=numofelements+(matrixsize[g]*matrixsize[g+1]);//calculating the number of elements in arguments
				}//closing for loop
				
				int[] matrixelements; //initializing an array of matrix elements
				matrixelements=new int[numofelements];// creating the size of the array to be numofelements
				for(int f=0;f<numofelements;f++){//for loop to iterate through
					matrixelements[f]=Integer.parseInt(args[argumentnumber+1]);//appending each argument that is a matrix element to the array
					argumentnumber++;//incrementing my accumulator argumentnumber
				}//closing for loop
		
				int[] currentsize; //initialize an array of the sizes of the two matrices multiplied at the current time
				currentsize=new int[4];// create the size of the array to be 4
				currentsize[0]=matrixsize[0];//set the number of rows in first matrix to be index 0
				currentsize[1]=matrixsize[1];//set the number of columns in first matrix to be index 1
				currentsize[2]=matrixsize[2];//set the number of rows in second matrix to be index 2
				currentsize[3]=matrixsize[3];//set the number of columns in second matrix to be index 3
		
				int numindiceanswers=0;// initializing a variable with a value of 1
				int accum=1;//creating variable accum to help calculate numindiceanswers when multiplying matrixsize elements together
				for(int s=0;s<N;s++){//iterating through N(number of matrices) times
					numindiceanswers=numindiceanswers+(matrixsize[0]*matrixsize[accum]);//calculating the size of every answer when multiplying plus original matrix for the size of our newmatrix
					accum=accum+2;//incrementing accum for the calculation
				}//closing for loop
		
				int[] newmatrix;//creating a array called newmatrix that starts with the first matrix and appends the answers of each matrix multiplication 
				newmatrix= new int[numindiceanswers];//creating the size of newmatrix
				int accum1=0;//creating a variable to keep track of which newmatrix index you are at
				int sizeoffirstmatrix=matrixsize[0]*matrixsize[1];//calculating size of first matrix with matrixsize
				for(int y=0;y<sizeoffirstmatrix;y++){//iterating through in order to append first matrix elements to newmatrix
					newmatrix[y]=matrixelements[y];//placing first matrix in newmatrix
					accum1++;//incrementing my accum1 in order to keep indice value for newmatrix
				}//closing for loop

				int accum2=0; //accum2 in order to keep track of how many times we need to multiply matrices
				int accum3=4; //accum3 is to get an indice number to switch current sizes after each multiplication
				int sumrow=0;//initializing the sum of each matrix multiplication
		
				int accum4=0;//used in order to check what time through the while loop it is
				int indicetracker=accum1; //initializing indicetracker as accum1 which was measuring indices from before
				int indicetracker2=0;//used increment indicetracker in order to have the right value of matrix elements when multiplying several matrices
				int insert=0;//initialized and used in order to use currentsize's to update an index
				int append=0;//initialized and used in order to use currentsize's to update an index
		
				while (multiply & accum2<(N-1)){// while loop runs if multiply is true and it hasn't multiplied the same matrices multiple times
					int indicecorrecter=0;//increment to get proper indice for newmatrix
					if (accum2>0){//if second or more time through this loop, updates values
						indicetracker=sizeoffirstmatrix+(currentsize[2]*currentsize[3])+indicetracker2;//updates indicetracker for each new multiplication
						indicetracker2=indicetracker2+(currentsize[2]*currentsize[3]);//update indicetracker2 for each new multiplication
					}//closing if statement
					if (accum4>0){//if second or more time through this loop, updates values
						append=append+(currentsize[2]*currentsize[3]);//updates append for indice correction
						currentsize[1]=currentsize[3];//changes my currentsize for the new multiplication
						currentsize[2]=matrixsize[accum3];//changes to next matrix for a new multiplication
						currentsize[3]=matrixsize[accum3+1];//changes to next matrix for a new multiplication
						accum3=accum3+2;//increments accum3 for if there is another multiplication to be done	
					}//closing if statement
				
					for(int j=0;j<currentsize[0];j++){//loops for all rows in first matrix
						int nextcolumn=0;//initializing variable nextcolumn
						for(int e=0;e<currentsize[3];e++){//loops for all columns in 2nd matrix  
							int nextrow=0;// initializing variable nextrow
							for(int d=0;d<currentsize[1];d++){//loops for one sum
								sumrow=sumrow+(newmatrix[nextrow+indicecorrecter+insert]*matrixelements[indicetracker]);//suming elements together for matrix multiplication
								indicetracker=indicetracker+currentsize[3];//updating indicetracker
								nextrow++;//increment to get next row of the 2nd matrix
							}//closing 3rd for loop
							newmatrix[accum1]=sumrow;//assigns sumrow to new matrix at current indice
							sumrow=0;//for first element multiplied sum reset to 0
							accum1=accum1+1;//increment for indice number accum1
							nextcolumn=nextcolumn+1;//increments to get next column of 2nd matrix
							indicetracker=sizeoffirstmatrix+nextcolumn+append;//updating indicetracker
						}//closing 2nd for loop
						indicecorrecter=indicecorrecter+currentsize[1];//updating indicecorrecter
						indicetracker=sizeoffirstmatrix+append;//resets indicetracker for next loop
					}//closing 1st for loop
					insert=insert+(currentsize[0]*currentsize[1]);//updating insert for newmatrix indices
					accum4++;//increments accum4
					accum2++;//increment accum2
				}//closing while loop	
		
				int elementsinmultipliedmatrix=matrixsize[0]*matrixsize[(N*2)-1];//calculates elements in final answer
				int indice=numindiceanswers-elementsinmultipliedmatrix;//calculating what indice the answer starts at in newmatrix
				int[] MultipliedMatrix;//creates array for answer
				MultipliedMatrix=new int[elementsinmultipliedmatrix];//creates size being the size of the answer
				for(int b=0;b<elementsinmultipliedmatrix;b++){//loop through all elements that are part of the answer
					MultipliedMatrix[b]=newmatrix[indice];//appending each answer to the new array called MultipliedMatrix
					indice=indice+1;//increment indice
				}//closing for loop
				
				int[][] answer1;//initializing a 2-D matrix
				answer1=new int[matrixsize[0]][matrixsize[(N*2)-1]];//putting in size of 2-D array
				int e=0;//accumulator for 1-D array indice
				for(int c=0;c<matrixsize[0];c++){//1st for loop to run through a row
					for(int b=0;b<matrixsize[(N*2)-1];b++){//2nd for loop to run through a column
						answer1[c][b]=MultipliedMatrix[e];//changing the 1-D matrix into a 2-D matrix
						e++;//incrementing e
					}//closing for loop
				}//closing for loop
			
				if ((checkinginvertibility(answer1)==true) && (matrixsize[0]==matrixsize[(N*2)-1])){//checking if matrix can be inverted
					Inversion(answer1);//calling the function called Inverse with the 2-D array
					
				}//closing if statement
				else{//if matrix is not invertible
					System.out.println("The matrix is not invertible");//printing that the matrix is non invertible
				}//closing the else statement
	
			
			}//closing if statement
		}//closing else statement
	}//closing the function
	
	public static int determinant(int[][] answer){//creating a new function that calculates the determinant of a 2x2 matrix
		int determinant1=0;//initializing a variable that stores the determinant to 0
		
		if (answer.length==1){//if statement for a base case/1x1 matrix
			determinant1=answer[0][0];//assigning determinant1 to answer at initial index	
		}//closing if statement
		
		else if(answer.length==2){//if the matrix is of size 2x2
			determinant1+= ((answer[0][0])*(answer[1][1]))-((answer[0][1])*(answer[1][0]));//calculation to find determinant of a 2x2
		}//closing if else statement
		
		else{//opening a condition if matrix is bigger than a 2x2
			int k=answer.length-2;//initializing a calculation constant
			double L=-2.0;//initializing another calculation constant
			for(int i=0;i<(answer.length+L)/k;i++){//for loop through each row necessary
				for(int j=0;j<answer.length;j++){//for loop through each column necessary
					determinant1+=answer[i][j]*determinant(makematrixsmaller(answer,i,j))*Math.pow(-1, (i+j));//calculates the determinant of each smaller matrix making it recursive and calling itself and giving it the proper sign
					
				}//closing for loop
			}//closing for loop
		}//closing else statement
		return determinant1;//returning the determinant of that index
	}//closing the function called determinant
	
	public static int[][] transpose(int[][] answer){//creating a function that transposes the cofactor
		int[][] transpose = new int [answer.length][answer.length];//creating a new 2-D array called transpose with the same size as cofactor
		for(int r=0;r<answer.length;r++){//for loop iterating through the rows
			for (int c=0;c<answer.length;c++){//for loop iterating through the columns
				transpose[r][c] = cofactor(answer)[c][r];//switching the index of rows, columns for columns,rows
				
			}//closing for loop
		}//closing for loop
		return transpose;//returning the array called transpose
	}//closing function called transpose
	
	public static int[][] makematrixsmaller(int[][] answer, int x, int y){//initializing a function to decrease the size of a matrix
		int[][] smallmatrix;//creating a 2-D array called small matrix
		smallmatrix=new int[answer.length-1][answer.length-1];//initializing the size of the matrix to be 1 indice smaller in both directions
		int row=0;//initializing a counter for the rows
		for(int i=0;i<answer.length;i++){//for loop to iterate through rows
			int column=0;//initializing a counter for the columns
			for(int j=0;j<answer.length;j++){//for loop to iterate through columns
				if((i!=x) && (j!=y)){//excluding the row and column that you are locating the index on
					smallmatrix[row][column]=answer[i][j];//appending all the rest of the values with 1 row and column eliminated
					column++;//incrementing column	
				}//closing if statement
			}//closing for loop
			if(i!=x){//if statement to check if i does not equal x
				row++;//incrementing row
			}//closing if statement
		}//closing for loop
		return smallmatrix;//returning the new smaller matrix
	}//closing the function makematrixsmaller
	
	public static int[][] cofactor(int[][] answer){//initializing a function to calculate the cofactor
		int[][] cofactor1= new int [answer.length][answer.length];//creating a 2-D array to store it in
		for(int r=0;r<answer.length;r++){//for loop to iterate through the rows
			for (int c=0;c<answer.length;c++){//for loop to iterate through the columns
				cofactor1[r][c]=(int) (determinant(makematrixsmaller(answer,r,c))*Math.pow(-1, r+c));//calculating the determinant at every index and storing it in the array as a cofactor multiplied by the proper sign
			}//closing for loop
		}//closing for loop
			
		return cofactor1;//returning the array called cofactor1
	}//closing the function called cofactor
	
	public static boolean checkinginvertibility(int[][] answer){//function to check if determinant is equal to 0 in order to decide whether to invert the matrix or not
		boolean run=true;//initialize run to true
		if(determinant(answer)==0){//checking if the determinant of the matrix is equal to 0
			System.out.println("The matrix is not invertible");//print that the matrix is not invertible
			run=false;//set run equal to false so that it won't calculate the inverse of the matrix
		}//closing if statement
		return run;//returning the boolean value of run
	}//closing function called check
	
	public static double[][] Inversion(int[][] answer){//creating a function to calculate the inverse of a matrix
		double[][] inverse= new double[answer.length][answer.length];//creating a 2-D array with the size of answer.length
		if (answer.length==1){//if it is a 1x1 matrix
			inverse[0][0]=(1.0/answer[0][0]);//inversion is 1.0/the value of that index
		}//close if statement
		else{//if it is bigger than a 1x1 matrix
			for(int i=0;i<answer.length;i++){//for loop to iterate through the rows
				for(int j=0;j<answer.length;j++){//for loop to iterate through the columns
					inverse[i][j]= Math.round((1.0/determinant(answer))*(transpose(answer)[i][j])*100)/100.0;//furmula to calculate the inverse of an index of an array and rounding to 3 decimal places
				}//closing for loop
			}//closing for loop
		}//closing else statement
		for(int r=0;r<answer.length;r++){//for loop to iterate through the rows
			for (int c=0;c<answer.length;c++){//for loop to iterate through the columns
				System.out.print(inverse[r][c]+" ");//prints the inverse on the same line with a space in between
			}//closing for loop
		}//closing for loop
		return inverse;//returning the inverse
	}//closing the function called Inversion
}//closing class name
