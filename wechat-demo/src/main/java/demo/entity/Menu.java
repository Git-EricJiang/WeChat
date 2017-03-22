package demo.entity;

import java.util.Arrays;

public class Menu {
	private ComplexButton[] button;

	public ComplexButton[] getButton() {
		return button;
	}

	public void setButton(ComplexButton[] button) {
		this.button = button;
	}

	@Override
	public String toString() {
		return "Menu [button=" + Arrays.toString(button) + "]";
	}
}
