package com.example.friendlypaws.friendlypaws;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PROGRAMADORA on 11/10/2018.
 */

public class TablaDinamica {
    private TableLayout tableLayout;
    private Context context;
    private String[]header;
    private ArrayList<String[]>data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;
    private boolean multiColor = false;
    int primerColor,  segundoColor;
    public TablaDinamica(TableLayout tableLayout,Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader(String[]header){
        this.header = header;
        createHeader();
    }

    public void addData(ArrayList<String[]>data){
        this.data = data;
        createDataTable();
    }

    private void newRow(){
        tableRow = new TableRow(context);
    }

    private void newCell(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(20);
        txtCell.setPadding(5,15,5,15);
    }
    private void newCellData(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.LEFT);
        txtCell.setTextColor(Color.BLACK);

        txtCell.setTextSize(18);
        txtCell.setPadding(5,15,5,15);
    }
    private void createHeader(){
        indexC=0;
        newRow();
        while (indexC   <header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }


    private void createDataTable(){
        String info;
        for (indexR=1;indexR<=data.size();indexR++){
            newRow();
            for ( indexC=0;indexC<header.length;indexC++  ){
                newCellData();
                String[] colums = data.get(indexR-1);
                info=(indexC<colums.length)?colums[indexC]:"";
                txtCell.setText(info);
                tableRow.addView(txtCell,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }

    private void addItems(String[]item){
        String info;
        data.add(item);
        indexC=0;
        newRow();
        while (indexC<header.length){
            newCell();
            info=(indexC<item.length)?item[indexC]:"";
            txtCell.setText(info);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow,data.size());
        reColorear();
    }

    public void colorHeader(int color){
        indexC=0;
        while (indexC   <header.length){
           txtCell = getCell(0, indexC++);
           txtCell.setBackgroundColor(color);
        }
    }
    public void colorData(int primerColor, int segundoColor){
        for (indexR=1;indexR<data.size()+1;indexR++){
            multiColor=!multiColor;
            for ( indexC=0;indexC<header.length;indexC++  ){
                txtCell=getCell(indexR, indexC);
                txtCell.setBackgroundColor((multiColor)?primerColor:segundoColor);
            }
        }
        this.primerColor= primerColor;
        this.segundoColor=segundoColor;
    }
    public void reColorear(){
        indexC=0;
        multiColor=!multiColor;
        while (indexC   <header.length){
            txtCell = getCell(data.size()-1, indexC++);
            txtCell.setBackgroundColor((multiColor)?primerColor:segundoColor);

        }
    }
    private TableRow getRow(int index){
            return (TableRow)tableLayout.getChildAt(index);
    }
    private TextView getCell(int rowIndex, int columIndex){
        tableRow= getRow(rowIndex);
        return (TextView)tableRow.getChildAt(columIndex);
    }


    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;

        return params;
    }
}
