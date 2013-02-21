package admin.models;

import models.metier.Question;
import models.metier.Reponse;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class AdminReponseModel extends AbstractTableModel{

	private final String[] COLONNES = {"Intitulé de la réponse", "Juste"};
	private Vector<Reponse> data;

	public AdminReponseModel() {
		data = new Vector<Reponse>();
	}

	public Vector<Reponse> getData() {
		return data;
	}

	public void setData(Vector<Reponse> data) {
		this.data = data;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return this.COLONNES.length;
	}

	@Override
	public int getRowCount() {
		if (this.data == null) {
			return 0;
		}
		return this.data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reponse r = this.data.get(rowIndex);

		Object o = null;
		
		switch (columnIndex) {
		case 0:
			o = r.getIntitule();
		break;
		case 1:
			o = (r.isJuste()) ? "OUI" : "NON";
		break;
		}

		return o;
	}
	
	@Override
	public String getColumnName(int colIndex) {
		return this.COLONNES[colIndex];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}


}
