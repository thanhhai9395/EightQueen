package com.bobby.eightqueen;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int n=8;

    private TableLayout ChessBoard;
    private Tile[][] tiles=new Tile[n][n];
    private TextView tvlog;

    private int Position[]=new int[n];
    private int Columns[]=new int[n];
    private int SubDiagonal[]=new int[2*n-1];
    private int PlusDiagonal[]=new int[2*n-1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChessBoard=(TableLayout)findViewById(R.id.ChessBoard);
        tvlog=(TextView)findViewById(R.id.log);



        CreateChessBoard();


    }

    private void CreateChessBoard()
    {
        //ChessBoard=null;
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int size=(int)((dm.widthPixels)/n);

        TableRow.LayoutParams params=new TableRow.LayoutParams(size,size);
        TableLayout.LayoutParams params1=new TableLayout.LayoutParams((int)((dm.widthPixels)),size);

        for(int i=0;i<n;i++){
            TableRow tableRow=new TableRow(this);
            for(int j=0;j<n;j++){

                tiles[i][j]=new Tile(this,false);
                tiles[i][j].setLayoutParams(params);

                //tiles[i][j].setPadding(2,2,2,2);

                if((i+j)%2 !=0) {
                    tiles[i][j].setBackgroundColor(getResources().getColor(R.color.le));
                }
                else {
                    tiles[i][j].setBackgroundColor(getResources().getColor(R.color.chan));
                }

             //   Init();

                final int row = i;
                final int column = j;
                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


      //                  tiles[row][column].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        try {
                            Init();
                            Try(0);
                            ShowResult();
                        }
                        catch (Exception e)
                        {
                            tvlog.setText(e.toString());
                        }
                        Toast.makeText(MainActivity.this, row +" "+ column, Toast.LENGTH_SHORT).show();

                    }
                });

                tableRow.addView(tiles[i][j]);
            }
            tableRow.setLayoutParams(params1);
            ChessBoard.addView(tableRow);
        }
    }

    private void Init()
    {
        for (int i=0;i<n;i++)
        {
            Position[i]=-1;
            Columns[i]=1;
        }
        for(int i=0;i<(2*n-1);i++)
        {
            PlusDiagonal[i]=1;
            SubDiagonal[i]=1;
        }
    }

    private void Try(int i)
    {
        for(int j=0;j<n;j++)
        {
            if(Columns[j]==1 && PlusDiagonal[i+j]==1 && SubDiagonal[i-j+n-1]==1)
            {
                Position[i]=j;
                Columns[j]=0;
                PlusDiagonal[i+j]=0;
                SubDiagonal[i-j+n-1]=0;

                if(i<n)
                {
                    Try(i+1);

                    Position[i]=-1;
                    Columns[j]=1;
                    PlusDiagonal[i+j]=1;
                    SubDiagonal[i-j+n-1]=1;

                }
                else
                {
                    for(int k=0;k<n;k++)
                    {
                        tiles[k][Position[k]].setQueen(true);
                        tiles[k][Position[k]].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                    return;
                }
            }
        }
    }

    private void ShowResult()
    {
        for(int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                if(tiles[i][j].isQueen())
                    //tiles[i][j].setBackgroundResource(R.drawable.chess);
                    tiles[i][j].setBackgroundColor(getResources().getColor(R.color.colorAccent));

        
    }
}
