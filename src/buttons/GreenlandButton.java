package buttons;

public class GreenlandButton extends TerritoryButton
{
	private static final long serialVersionUID = -4017492434666001404L;

	public GreenlandButton()
	{
		this.setSize(30, 90);

		shape.addPoint(0, 0);
		shape.addPoint(30, 0);
		shape.addPoint(30, 90);
		shape.addPoint(0, 90);

		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
	}
}
