package day0904;

public class BenzDao extends AbstractDao<Benz> {

	@Override
	protected Class<Benz> getEntityClass() {
		return Benz.class;
	}

}
