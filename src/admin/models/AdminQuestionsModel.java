package admin.models;

import models.metier.Question;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class AdminQuestionsModel extends AbstractTableModel{

	private final String[] COLONNES = {"Intitulé de la question", "Niveau"};
	private Vector<Question> data;

	public AdminQuestionsModel() {
		data = new Vector<Question>();
	}

	public Vector<Question> getData() {
		return data;
	}

	public void setData(Vector<Question> data) {
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
		Question q = this.data.get(rowIndex);

		Object o = null;
		
		switch (columnIndex) {
		case 0:
			o = q.getIntitule();
			break;
		case 1:
			o = q.getNiveau();
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
