package se360.shortlinker.swingclient.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LinksTableModel extends AbstractTableModel {
    private List<Link> links;
    private String[] columnNames = {"id", "aField01", "aField02", "aBigDecimal", "aDouble"};
    public LinksTableModel(List<Link> links) {
        this.links = links;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return links.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Link link = links.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> link.getId();
//            case 1 -> link.getShortLink();
//            case 2 -> link.getLongLink();
//            case 3 -> link.getCreatedAt();
//            case 4 -> link.getUpdatedAt();
            default -> throw new IllegalStateException("Unexpected column index: " + columnIndex);
        };
    }
}
