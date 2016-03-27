package listeners;

import java.util.ArrayList;

public interface IupdateInfo {
	public void updateTitle(String newTitle);
	public void updateText(String newText);
	public void updateArrayText(ArrayList<String> texts);
	public void updatePack();
	public void updateToMenuPrincipal();
	public void updateToMenuClique();
}
