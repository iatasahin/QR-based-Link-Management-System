package se360.shortlinker.swingclient.model;

import se360.shortlinker.swingclient.Main;

import javax.swing.table.AbstractTableModel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class LinksTableModel extends AbstractTableModel {
    private final List<Link> links;
    private final String[] columnNames = {"ID", "Name", "#Clicks", "URL", "CreationTime"  /*, ""*/};
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

    public void removeRow(int row){
        links.remove(row);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Link link = links.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> link.getId();
            case 1 -> link.getName();
            case 2 -> link.getClicks();
            case 3 -> link.getUrl();
            case 4 -> {
                Instant instant = link.getCreatedAt();
                LocalDateTime createdAt = LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault());
                yield createdAt.format(Main.formatter);
            }
            default -> throw new IllegalStateException("Unexpected column index: " + columnIndex);
        };
    }
}
