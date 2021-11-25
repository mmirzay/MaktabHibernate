package day0904;

public class Main {
	public static void main(String[] args) {

		AbstractDao<Benz> benzDao = new BenzDao();

		EBenz ebenz = new EBenz(2);
////		ebenz.setId(99);
//		ebenz.setColor(BenzColors.BLUE);
//		benzDao.save(ebenz);

		EBenz found = (EBenz) benzDao.findById(1);
		System.out.println("found ebenz color: " + found.getColor());

	}
}