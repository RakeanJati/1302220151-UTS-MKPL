package lib;

public class TaxFunction {

	private static final int PTKP_SINGLE = 54_000_000;
	private static final int PTKP_MARRIED_ADDITION = 4_500_000;
	private static final int PTKP_CHILD_ADDITION = 4_500_000;
	private static final int MAX_CHILDREN_COUNT = 3;
	private static final double TAX_RATE = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
	                               int deductible, boolean isMarried, int numberOfChildren) {

		if (numberOfMonthWorking < 0 || numberOfMonthWorking > 12) {
			throw new IllegalArgumentException("Jumlah bulan bekerja harus antara 0 hingga 12.");
		}

		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;


		int nonTaxableIncome = PTKP_SINGLE;
		if (isMarried) {
			nonTaxableIncome += PTKP_MARRIED_ADDITION;
		}

		nonTaxableIncome += PTKP_CHILD_ADDITION * Math.min(numberOfChildren, MAX_CHILDREN_COUNT);

		int taxableIncome = totalIncome - deductible - nonTaxableIncome;

		if (taxableIncome <= 0) {
			return 0;
		}

	
		return (int) Math.round(taxableIncome * TAX_RATE);
	}
}
