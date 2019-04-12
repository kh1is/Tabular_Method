package Stage2;

import java.util.LinkedList;

public class groupingModule {

	/* constructor */

	public groupingModule(String s1, String s2) {
		minterms = s1;
		dontcares = s2;
	}

	/* Attributes */

	public int noVariables;
	public SinglyLinkedList[] Column; // created in method setColumn
	public SinglyLinkedList Temp = new SinglyLinkedList();
	public SinglyLinkedList PrimeImplicants = new SinglyLinkedList();
	public String minterms = "";
	public String dontcares = "";
	public int[] mintermsArr;
	public int[] dontCaresArr;

	/* The Main method */

	public SinglyLinkedList getPrimeImplicants(String minterms, String dontCares) {
		if (dontCares == null) {
			dontCaresArr = new int[0];
		} else {
			dontCaresArr = set_Minterms_OR_DontCares_Array(dontCares);
		}
		mintermsArr = set_Minterms_OR_DontCares_Array(minterms);

		noVariables = getNoVariables(mintermsArr, dontCaresArr);
		setColumn(mintermsArr, dontCaresArr);
		combineGroups();

		return PrimeImplicants;

	}

	/* Extra methods */

	
	public int Check_getSizeOfArray(String minterms_OR_dontCares) {

		char[] x = minterms_OR_dontCares.toCharArray();

		int size = 0;

		if (x.length == 1) {
			return 1;
		}

		if ((x[0] < '0' || x[0] > '9') || (x[x.length - 1] < '0' || x[x.length - 1] > '9')) {
			return -1;
		} else if ((x[x.length - 1] >= '0' || x[x.length - 1] <= '9') && x[x.length - 2] == ',') {
			size++;
		}

		for (int i = 0; i < x.length - 1; i++) {
			if ((x[i] >= '0' && x[i] <= '9') && (x[i + 1] == ',')) {
				if ((i - 1) > 0 && x[i - 1] == ',') {
					size++;
				} else if (i == 0) {
					size++;
				}
			} else if ((x[i] >= '0' && x[i] <= '9') && (x[i + 1] >= '0' && x[i + 1] <= '9')) {
				size++;
			} else if (x[i] == ',') {
				if (x[i + 1] == ',') {
					return -1;
				} else {
					continue;
				}
			} else if ((x[i] >= '0' && x[i] <= '9') && (x[i - 1] >= '0' && x[i - 1] <= '9')) {
				continue;
			}
		}
		return size;
	}

	public int[] set_Minterms_OR_DontCares_Array(String minTermsStr) {

		int[] result;

		if (Check_getSizeOfArray(minTermsStr) == -1) {
			return null;
		} else {
			result = new int[Check_getSizeOfArray(minTermsStr)];
		}

		char[] x = minTermsStr.toCharArray();

		int y = 0;
		for (int i = 0; i < x.length - 1; i++) {
			if ((x[i] >= '0' && x[i] <= '9') && (x[i + 1] == ',')) {
				result[y] = x[i] - 48;
				y++;
			} else if ((x[i] >= '0' && x[i] <= '9') && (x[i + 1] >= '0' && x[i + 1] <= '9')) {
				result[y] = (x[i] - 48) * 10 + (x[i + 1] - 48);
				i++;
				y++;
			}
			else if((i == x.length-2) && (x[i] == ',') && (x[i + 1] >= '0' && x[i + 1] <= '9')){
				result[y] = x[i+1] - 48;
				y++;
			}
		}

		return result;
	}

	public int getNoVariables(int[] arr1, int[] arr2) {

		int noVariables;
		int[] mintermsAndDontCares = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++) {
			mintermsAndDontCares[i] = arr1[i];
		}
		int j = 0;
		for (int i = arr1.length; i < arr1.length + arr2.length; i++) {
			mintermsAndDontCares[i] = arr2[j];
			j++;
		}

		double max = mintermsAndDontCares[0];
		for (int i = 0; i < mintermsAndDontCares.length; i++) {
			if (mintermsAndDontCares[i] > max) {
				max = mintermsAndDontCares[i];
			}
		}

		noVariables = (int) Math.ceil((Math.log(max) / Math.log(2)) + .00000001);

		return noVariables;
	}

	public int[] binaryRepresentationOfanInteger(int integerToConvert) {

		int[] arr = new int[noVariables];

		String s = Integer.toBinaryString(integerToConvert);
		char[] arr1 = s.toCharArray();

		int i = arr.length - 1;
		int j = arr1.length - 1;

		while (i >= 0 && j >= 0) {
			if (arr1[j] == '1') {
				arr[i] = 1;
			}
			i--;
			j--;

		}

		return arr;
	}

	public boolean canBeGrouped(int[] arr1, int[] arr2) {

		int hamingDistance = 0;
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				hamingDistance++;
			}
		}
		if (hamingDistance == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int[] combine(int[] arr1, int[] arr2) {

		int[] result = new int[arr1.length];
		for (int i = 0; i < result.length; i++) {
			if (arr1[i] != arr2[i]) {
				result[i] = -1; // -1 means dash
			} else {
				result[i] = arr1[i];
			}
		}
		return result;
	}

	public void setColumn(int[] mintermsArr, int[] dontCaresArr) {

		LinkedList mintermsParticipated = new LinkedList();

		Column = new SinglyLinkedList[noVariables + 1];

		int numberOfOnes;

		for (int i = 0; i < mintermsArr.length; i++) {
			numberOfOnes = Integer.bitCount(mintermsArr[i]);
			if (Column[numberOfOnes] == null) {
				Column[numberOfOnes] = new SinglyLinkedList();
			}

			mintermsParticipated.add(mintermsArr[i]);
			Column[numberOfOnes].add(binaryRepresentationOfanInteger(mintermsArr[i]), mintermsParticipated);
			mintermsParticipated.clear();
		}

		for (int i = 0; i < dontCaresArr.length; i++) {

			numberOfOnes = Integer.bitCount(dontCaresArr[i]);
			if (Column[numberOfOnes] == null) {
				Column[numberOfOnes] = new SinglyLinkedList();
			}

			mintermsParticipated.add(dontCaresArr[i]);
			Column[numberOfOnes].add(binaryRepresentationOfanInteger(dontCaresArr[i]), mintermsParticipated);
			mintermsParticipated.clear();
		}

	}

	public void combineGroups() {

		LinkedList minP = new LinkedList();
		int addadomry = 0;
		for (int i = 0; i < Column.length; i++) {
			if ((Column[i] == null) || (Column[i].head == null)) {
				addadomry++;
			}
		}
		if (addadomry == Column.length) {
			return;
		}

		int counter = 0;

		while (counter <= noVariables) {

			ListNode i, j;
			if (Column[counter] == null || Column[counter].head == null) {
				counter++;
				continue;
			}

			i = Column[counter].head;

			if (((counter + 1) <= noVariables) && (Column[counter + 1] != null) && (Column[counter + 1].head != null)) {

				while (i != null) {
					j = Column[counter + 1].head;
					while (j != null) {
						if (canBeGrouped(i.binaryRep, j.binaryRep)) {
							if (Temp.contains(combine(i.binaryRep, j.binaryRep)) == false) {
								for (int k = 0; k < i.mintermsParticipated.size(); k++) {
									minP.add(i.mintermsParticipated.get(k));
								}
								for (int k = 0; k < j.mintermsParticipated.size(); k++) {
									minP.add(j.mintermsParticipated.get(k));
								}
								Temp.add(combine(i.binaryRep, j.binaryRep), minP);
								minP.clear();
							}
							i.checked = true;
							j.checked = true;
						}
						j = j.getNext();
					}
					if (i.checked == false) {
						for (int k = 0; k < i.mintermsParticipated.size(); k++) {
							minP.add(i.mintermsParticipated.get(k));
						}
						PrimeImplicants.add(i.binaryRep, minP);
						minP.clear();
					}
					i = i.getNext();

				}

				Column[counter].clear();
				ListNode k = Temp.head;
				while (k != null) {
					for (int u = 0; u < k.mintermsParticipated.size(); u++) {
						minP.add(k.mintermsParticipated.get(u));
					}
					Column[counter].add(k.binaryRep, minP);
					minP.clear();
					k = k.getNext();
				}
				Temp.clear();

			} else {
				while (i != null) {
					if (i.checked == false) {
						for (int k = 0; k < i.mintermsParticipated.size(); k++) {
							minP.add(i.mintermsParticipated.get(k));
						}
						PrimeImplicants.add(i.binaryRep, minP);
						minP.clear();
						i = i.getNext();
					} else {
						i = i.getNext();
					}

				}
				Column[counter].clear();
			}

			counter++;
		}

		combineGroups();
	}

}
