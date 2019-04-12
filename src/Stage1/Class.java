package Stage1;
import java.util.LinkedList;

public class Class {

	private Object[][] chart;
	private Object[][] newChart;
	private Linked essentialPrimes = new Linked();
	private Linked newLinked = new Linked();
	private LinkedList newArray = new LinkedList();
	private Link function = new Link();
	private Link result = new Link();
	private Linked temp1 = new Linked();
	private LinkedList temp2 = new LinkedList();
	private Linked minimumPrimes = new Linked();
	private Linked[] allSolutions;

	public Class(int arrLength, int linkedLength) {
		chart = new Object[linkedLength][arrLength];

	}

	public void setChart(int[] arr, Linked l) {
		LinkedListNodes k = l.head;
		for (int i = 0; i < chart.length; i++) {
			for (int f = 0; f < k.minterm.size(); f++) {
				for (int j = 0; j < chart[i].length; j++) {

					if (k.minterm.get(f) == (Object) arr[j]) {
						chart[i][j] = "x";
					}

				}
			}
			k = k.next;
		}

	}

	public void markEssential(Linked l) {
		for (int j = 0; j < chart[0].length; j++) {
			int counter = 0;
			int row = 0;
			LinkedListNodes f = null;
			LinkedListNodes k = l.head;
			for (int i = 0; i < chart.length; i++) {
				if (chart[i][j] == "x") {
					counter++;
					row = i;
					f = k;
				}
				k = k.next;
			}
			if (counter == 1) {
				markRow(row, chart);
				mark(chart);
				essentialPrimes.add(f.minterm, f.Binary);
			}
		}

	}

	public void markRow(int row, Object[][] charts) {
		for (int i = 0; i < charts[row].length; i++) {
			if (charts[row][i] == "x") {
				charts[row][i] = "-";
			}
		}
	}

	public void mark(Object[][] charts) {
		for (int i = 0; i < charts.length; i++) {
			for (int j = 0; j < charts[i].length; j++) {
				if (charts[i][j] == "-") {
					markColumns(j, charts);
				}
			}
		}
	}

	public void markColumns(int columne, Object[][] charts) {
		for (int i = 0; i < charts.length; i++) {
			if (charts[i][columne] == "x") {
				charts[i][columne] = "-";
			}
		}
	}

	public void newArray(int[] arr) {
		for (int j = 0; j < chart[0].length; j++) {
			int flag = 0;
			for (int i = 0; i < chart.length; i++) {
				if (chart[i][j] == "x") {
					flag = 1;
				}
			}
			if (flag == 1) {
				newArray.add(arr[j]);
			}
		}
	}

	public void newLinked(Linked l) {

		LinkedListNodes k = l.head;
		for (int i = 0; i < chart.length; i++) {
			LinkedListNodes f = null;
			int flag = 0;
			for (int j = 0; j < chart[0].length; j++) {
				if (chart[i][j] == "x") {
					flag = 1;
					f = k;
				}
			}
			if (flag == 1) {
				newLinked.add(f.minterm, f.Binary);
			}
			k = k.next;
		}
	}

	public void setNewChart() {

		newChart = new Object[newLinked.size()][newArray.size()];
		LinkedListNodes k = newLinked.head;
		for (int i = 0; i < newChart.length; i++) {
			for (int f = 0; f < k.minterm.size(); f++) {

				for (int j = 0; j < newChart[i].length; j++) {

					if (k.minterm.get(f) == newArray.get(j)) {
						newChart[i][j] = "x";
					}

				}
			}
			k = k.next;
		}

	}

	public void setFunction() {

		if (newChart.length > 0) {
			for (int j = 0; j < newChart[0].length; j++) {
				LinkedListNodes f = newLinked.head; // „·Â«‘ ·«“„…
				for (int i = 0; i < newChart.length; i++) {
					if (newChart[i][j] == "x") {
						temp2.add(i);
						temp1.add(temp2, f.Binary);

					}
					temp2.clear();

				}
				function.add(temp1, f.Binary);
				temp1.clear();
			}

		}

	}

	public void multiply(Linked l1, Linked l2) {

		LinkedListNodes f = l1.head;

		LinkedListNodes m = l1.head; // „·Â«‘ ·«“„…

		while (f != null) {

			LinkedListNodes k = l2.head;
			while (k != null) {

				for (int i = 0; i < f.minterm.size(); i++) {

					temp2.add(f.minterm.get(i));
				}
				for (int j = 0; j < k.minterm.size(); j++) {
					temp2.add(k.minterm.get(j));
				}

				temp1.add(temp2, m.Binary);
				temp2.clear();

				k = k.next;
			}
			f = f.next;

		}

		result.add(temp1, m.Binary);
		temp1.clear();
	}

	public boolean isRedundant(LinkedList l1) {
		int j = 0;
		int flag1 = 0;
		int flag2 = 0;
		while (j < l1.size() - 1) {
			for (int i = j + 1; i < l1.size(); i++) {
				if (l1.get(j) == l1.get(i)) {
					flag1 = 1;
					flag2 = 1;
					break;
				}

			}
			if (flag2 == 1) {
				break;
			}
			j++;
		}
		if (flag1 == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void removeRedundant(LinkedList l1) {
		int j = 0;

		int length = l1.size();
		while (j < length - 1) {
			for (int i = j + 1; i < length; i++) {
				if (l1.get(j) == l1.get(i)) {
					l1.remove(i);

					length--;
					i--;
				}

			}

			j++;
		}

	}

	public boolean isPrimesRedundant(LinkedList l1, LinkedList l2) {
		int flag = 0;
		if (l1.size() <= l2.size()) {

			for (int i = 0; i < l1.size(); i++) {
				flag = 0;
				for (int j = 0; j < l2.size(); j++) {
					if (l1.get(i) == l2.get(j)) {
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					return false;
				}
			}

		} else {

			for (int i = 0; i < l2.size(); i++) {
				flag = 0;
				for (int j = 0; j < l1.size(); j++) {
					if (l2.get(i) == l1.get(j)) {
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					return false;
				}

			}

		}
		if (flag == 1) {
			return true;
		} else {
			return false;
		}

	}

	public void replace() {

		function.clear();

		LinkedListNodes f = newLinked.head; // „·Â«‘ ·«“„…
		Node j = result.head;

		while (j != null) {
			function.add(j.arr, f.Binary);
			j = j.next;
		}

		result.clear();
	}

	public void Kolh() {
		if (function.size > 0) {
			Node i = function.head;
			LinkedListNodes f = newLinked.head; // „·Â«‘ ·«“„…
			if (i.next != null) {

				Node j = function.head;
				while (j != null) {

					if (j.next == null) {
						result.add(j.arr, f.Binary);
						break;
					} else {
						Node k = j.next;
						multiply(j.arr, k.arr);
					}

					j = j.next.next;

				}

				replace();

				Node k = function.head;

				while (k != null) {
					LinkedListNodes r = k.arr.head;
					while (r != null) {
						if (isRedundant(r.minterm)) {
							removeRedundant(r.minterm);
						}
						r = r.next;
					}

					k = k.next;
				}

				Node t = function.head;
				while (t != null) {

					LinkedListNodes x = t.arr.head;
					while (x.next != null) {
						LinkedListNodes y = x.next;
						while (y != null) {

							if (isPrimesRedundant(x.minterm, y.minterm)) {
								if (x.minterm.size() == y.minterm.size()) {

									if (x.check == false && y.check == false) {
										temp1.add(x.minterm, f.Binary);
										x.check = true;

									}
									y.check = true;
								} else if (x.minterm.size() < y.minterm.size()) {
									if (x.check == false) {
										temp1.add(x.minterm, f.Binary);
										x.check = true;

									}
									y.check = true;
								} else {
									if (y.check == false) {
										temp1.add(y.minterm, f.Binary);
										y.check = true;

									}
									x.check = true;
								}
							}

							y = y.next;
						}
						if (x.check == false) {
							temp1.add(x.minterm, f.Binary);
							x.check = true;
						}
						x = x.next;
						if (x.next == null) {
							if (x.check == false) {
								temp1.add(x.minterm, f.Binary);
								x.check = true;
							}
						}
					}
					t = t.next;
					result.add(temp1, f.Binary);
					temp1.clear();
				}

				replace();

				Kolh();

			} else {

				Node t = function.head;
				while (t != null) {

					LinkedListNodes x = t.arr.head;
					while (x.next != null) {
						LinkedListNodes y = x.next;
						while (y != null) {

							if (isPrimesRedundant(x.minterm, y.minterm)) {
								if (x.minterm.size() == y.minterm.size()) {

									if (x.check == false && y.check == false) {
										temp1.add(x.minterm, f.Binary);
										x.check = true;

									}
									y.check = true;
								} else if (x.minterm.size() < y.minterm.size()) {
									if (x.check == false) {
										temp1.add(x.minterm, f.Binary);
										x.check = true;

									}
									y.check = true;
								} else {
									if (y.check == false) {
										temp1.add(y.minterm, f.Binary);
										y.check = true;

									}
									x.check = true;
								}
							}

							y = y.next;
						}
						if (x.check == false) {
							temp1.add(x.minterm, f.Binary);
							x.check = true;
						}
						x = x.next;
						if (x.next == null) {
							if (x.check == false) {
								temp1.add(x.minterm, f.Binary);
								x.check = true;
							}
						}
					}
					t = t.next;
					result.add(temp1, f.Binary);
					temp1.clear();
				}

				replace();

			}
		}
	}

	public int count(int[] arr) {
		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				counter++;
			}

		}

		return counter;

	}

	public void minimumPrimes() {

		if (function.size > 0) {
			int min1 = 0;
			int min2 = 0;
			LinkedListNodes f = newLinked.head; // „·Â«” ·«“„…
			Node i = function.head;

			LinkedListNodes j = i.arr.head.next;
			LinkedListNodes m = i.arr.head;
			min1 = m.minterm.size();
			while (j != null) {

				if (min1 > j.minterm.size()) {
					min1 = j.minterm.size();
				}

				j = j.next;
			}

			while (m != null) {

				if (m.minterm.size() == min1) {
					minimumPrimes.add(m.minterm, f.Binary);
				}
				m = m.next;
			}

			LinkedListNodes s = minimumPrimes.head;
			int counter = 0;
			if (s.next != null) {

				while (s != null) {
					counter = 0;
					for (int c = 0; c < s.minterm.size(); c++) {
						LinkedListNodes l = newLinked.head;
						for (int y = 0; y < newChart.length; y++) {
							if ((int) s.minterm.get(c) == y) {

								counter += count(l.Binary);
							}

							l = l.next;
						}

					}
					s.counter = counter;
					s = s.next;
				}

				LinkedListNodes x = minimumPrimes.head.next;
				LinkedListNodes z = minimumPrimes.head;

				min2 = z.counter;

				while (x != null) {

					if (min2 > x.counter) {
						min2 = x.counter;
					}

					x = x.next;
				}

				temp1.clear();
				while (z != null) {

					if (z.counter == min2) {
						temp1.add(z.minterm, f.Binary);
					}
					z = z.next;
				}

				minimumPrimes.clear();

				LinkedListNodes v = temp1.head;

				while (v != null) {
					minimumPrimes.add(v.minterm, f.Binary);
					v = v.next;
				}
				temp1.clear();
			}
		}

	}

	public void solutions() {

		if (minimumPrimes.size() > 0) {
			allSolutions = new Linked[minimumPrimes.size()];
			for (int k = 0; k < minimumPrimes.size(); k++) {
				allSolutions[k] = new Linked();
			}

			LinkedListNodes s = minimumPrimes.head;
			int x = 0;
			while (s != null) {

				for (int i = 0; i < s.minterm.size(); i++) {
					LinkedListNodes l = newLinked.head;
					for (int j = 0; j < newChart.length; j++) {
						if ((int) s.minterm.get(i) == j) {

							allSolutions[x].add(l.minterm, l.Binary);
						}

						l = l.next;
					}

				}

				x++;
				s = s.next;
			}

			for (int i = 0; i < allSolutions.length; i++) {
				LinkedListNodes a = essentialPrimes.head;
				while (a != null) {

					allSolutions[i].add(a.minterm, a.Binary);

					a = a.next;
				}

			}
		} else {
			allSolutions = new Linked[1];
			allSolutions[0] = new Linked();

			LinkedListNodes k = essentialPrimes.head;
			while (k != null) {

				allSolutions[0].add(k.minterm, k.Binary);

				k = k.next;
			}

		}

	}

	public String nameSolutions(int n, int m) {
		String s = null;
		if (n == 0) {
			if (m == 0) {
				s = "A'";
			} else if (m == 1) {
				s = "B'";
			} else if (m == 2) {
				s = "C'";
			} else if (m == 3) {
				s = "D'";
			} else if (m == 4) {
				s = "E'";
			} else if (m == 5) {
				s = "F'";
			} else if (m == 6) {
				s = "G'";
			}

		}

		else {
			if (m == 0) {
				s = "A";
			} else if (m == 1) {
				s = "B";
			} else if (m == 2) {
				s = "C";
			} else if (m == 3) {
				s = "D";
			} else if (m == 4) {
				s = "E";
			} else if (m == 5) {
				s = "F";
			} else if (m == 6) {
				s = "G";
			}

		}

		return s;

	}

	public String nameFunction(int n) {
		StringBuilder ss = new StringBuilder();
		ss.append("p");
		ss.append(n + 1);

		return String.valueOf(ss);

	}

	public String nameOfFunction() {
		StringBuilder name = new StringBuilder();
		name.append("P = ");
		name.append("{");
		Node i = function.head;
		while (i != null) {
			name.append("(");
			LinkedListNodes j = i.arr.head;
			while (j != null) {
				for (int k = 0; k < j.minterm.size(); k++) {
					name.append(nameFunction((int) j.minterm.get(k)));
				}
				if (j.next != null) {
					name.append(" + ");
				}
				j = j.next;
			}
			name.append(")");
			i = i.next;
		}
		name.append("}");

		return String.valueOf(name);

	}

	public String nameOfMinimumPrimes() {
		StringBuilder name = new StringBuilder();
		name.append("Minimum P = ");
		name.append("{");
		LinkedListNodes j = minimumPrimes.head;
		name.append("(");
		while (j != null) {
			for (int k = 0; k < j.minterm.size(); k++) {
				name.append(nameFunction((int) j.minterm.get(k)));
			}
			if (j.next != null) {
				name.append(" + ");
			}

			j = j.next;
		}
		name.append(")");
		name.append("}");

		return String.valueOf(name);

	}

	public String nameOfSolution() {
		StringBuilder name = new StringBuilder();
		name.append("Minimum Solutions = ");
		name.append("{");
		for (int i = 0; i < allSolutions.length; i++) {
			name.append("(");
			LinkedListNodes k = allSolutions[i].head;

			while (k != null) {

				for (int f = 0; f < k.Binary.length; f++) {
					if (k.Binary[f] != -1) {
						name.append(nameSolutions(k.Binary[f], f));
					}
				}
				if (k.next != null) {
					name.append(" + ");
				}
				k = k.next;
			}
			name.append(")");
			if (i < allSolutions.length - 1) {
				name.append(" OR ");
			}
		}
		name.append("}");

		return String.valueOf(name);

	}

	public String nameOfChart(int[] arr, Linked l) {
		StringBuilder name = new StringBuilder();
		
		name.append("<Steps>:-");
		name.append("\n\n\n");
		name.append("<Prime Implicants Chart>:-");
		name.append("\n\n");
		LinkedListNodes t = l.head;
		for (int i = 0; i < chart.length; i++) {
			name.append("P");
			name.append(i);
			name.append("\t");
			for (int j = 0; j < t.minterm.size(); j++) {
				name.append(t.minterm.get(j) + " ");
			}
			name.append("\n");
			t = t.next;
		}

		name.append("\t\t\t");
		for (int h = 0; h < arr.length; h++) {
			name.append(arr[h] + "   ");
		}

		name.append("\n\n");
		LinkedListNodes k = l.head;
		for (int i = 0; i < chart.length; i++) {
			name.append("P");
			name.append(i);
			name.append("\t");
			for (int j = 0; j < k.Binary.length; j++) {
				if(k.Binary[j] == -1){
					name.append("-" + " ");
				}
				else{
					name.append(k.Binary[j] + " ");
				}
			}

			name.append("\t");
			for (int j = 0; j < chart[i].length; j++) {
				if (chart[i][j] == "x") {
					name.append(chart[i][j] + "   ");
				} else {
					name.append(" " + "   ");
				}
			}
			name.append("\n");
			k = k.next;
		}

		return String.valueOf(name);

	}

	public String nameOfMarkEssential(int[] arr, Linked l) {

		StringBuilder name = new StringBuilder();
		name.append("<After Eliminating Essentials>:-");
		name.append("\n\n");

		LinkedListNodes t = l.head;
		for (int i = 0; i < chart.length; i++) {
			name.append("P");
			name.append(i);
			name.append("\t");
			for (int j = 0; j < t.minterm.size(); j++) {
				name.append(t.minterm.get(j) + " ");
			}
			name.append("\n");
			t = t.next;
		}

		name.append("\t\t\t");
		for (int h = 0; h < arr.length; h++) {
			name.append(arr[h] + "   ");
		}
		name.append("\n\n");
		LinkedListNodes k = l.head;
		for (int i = 0; i < chart.length; i++) {
			name.append("P");
			name.append(i);
			name.append("\t");
			for (int j = 0; j < k.Binary.length; j++) {
				if(k.Binary[j] == -1){
					name.append("-" + " ");
				}
				else{
					name.append(k.Binary[j] + " ");
				}
			}

			name.append("\t");

			for (int j = 0; j < chart[i].length; j++) {
				if (chart[i][j] == "x") {
					name.append(chart[i][j] + "   ");
				} else if (chart[i][j] == "-") {
					name.append(chart[i][j] + "   ");
				} else {
					name.append(" " + "   ");
				}
			}
			name.append("\n");
			k = k.next;
		}

		return String.valueOf(name);

	}

	public String nameOfNewChart() {
		StringBuilder name = new StringBuilder();
		name.append("<Reduced Chart>:-");
		name.append("\n\n");

		LinkedListNodes t = newLinked.head;
		for (int i = 0; i < newChart.length; i++) {
			name.append("P");
			name.append(i);
			name.append("\t");
			for (int j = 0; j < t.minterm.size(); j++) {
				name.append(t.minterm.get(j) + " ");
			}
			name.append("\n");
			t = t.next;
		}

		name.append("\t\t\t");
		for (int j = 0; j < newArray.size(); j++) {
			name.append(newArray.get(j) + "   ");
		}

		name.append("\n\n");
		LinkedListNodes k = newLinked.head;
		for (int i = 0; i < newChart.length; i++) {
			name.append("P");
			name.append(i);
			name.append("\t");

			for (int j = 0; j < k.Binary.length; j++) {
				if(k.Binary[j] == -1){
					name.append("-" + " ");
				}
				else{
					name.append(k.Binary[j] + " ");
				}
			}

			name.append("\t");
			for (int j = 0; j < newChart[i].length; j++) {
				if (newChart[i][j] == "x") {
					name.append(newChart[i][j] + "   ");
				} else {
					name.append(" " + "   ");
				}
			}
			name.append("\n");
			k = k.next;
		}

		return String.valueOf(name);

	}

	public String nameOfEssentialPrimes(){
		StringBuilder name = new StringBuilder();
		name.append("Essential Primes = ");
		
		
		name.append("{");
		name.append("(");
		LinkedListNodes k = essentialPrimes.head;
		while (k != null) {
			for (int f = 0; f < k.Binary.length; f++) {
				if (k.Binary[f] != -1) {
					name.append(nameSolutions(k.Binary[f], f));
				}
			}
			if (k.next != null) {
				name.append(" + ");
			}
			
			k = k.next;
		}
		
		name.append(")");
		name.append("}");
		return String.valueOf(name);
		
		
	}
	
	
	
}
