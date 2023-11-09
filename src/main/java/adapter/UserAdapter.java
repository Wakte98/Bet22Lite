package adapter;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import domain.ApustuAnitza;
import domain.Apustua;
import domain.Registered;

public class UserAdapter extends AbstractTableModel{
	private Registered user;
    private String[] headers = new String[]{"Event", "Event Date", "Question", "Bet(€)"};
	private ArrayList<Apustua> apuestas;
	public UserAdapter(Registered r) {
		this.user=r;
		apuestas = new ArrayList<Apustua>();
		for(ApustuAnitza a : user.getApustuAnitzak()) {
			apuestas.addAll(a.getApustuak());
		}
	}	
	@Override
	public Object getValueAt(int row, int col) {
	      Object value = "";
	      switch (col) {
	        case 0:
	            value = apuestas.get(row).getKuota().getQuestion().getEvent().getDescription();
	            break;
	        case 1:
	            value = apuestas.get(row).getApustuAnitza().getData();
	            break;
	        case 2:
	            value = apuestas.get(row).getKuota().getQuestion().getQuestion();
	            break;
	        case 3:
	            value = apuestas.get(row).getApustuAnitza().getBalioa();
	            break;
	      }
		return value;
	}
	@Override
	public String getColumnName(int col) {
		return headers[col];
	}
	@Override
	public int getColumnCount() {
		return 4;
	}
	@Override
	public int getRowCount() {
		return apuestas.size();
	}
}