package com.sana.connect3;

import android.media.Image;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    // 0= disc2 and 1=disc5
    int activePlayer=0;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    boolean gameOver=false;
    int winningPositions[][]={{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{6,4,2}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tag=Integer.parseInt(counter.getTag().toString());
        if(gameState[tag]==2 && !gameOver) {
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                gameState[tag] = 0;
                counter.setImageResource(R.drawable.disc2);
                activePlayer = 1;
            } else {
                gameState[tag] = 1;
                counter.setImageResource(R.drawable.disc3);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(200);
        }//Log.i("info","hey"+activePlayer);

        for(int[] win : winningPositions )
        {
            String winnerID="Player 1";
            if(gameState[win[0]]==gameState[win[1]] && gameState[win[1]]==gameState[win[2]] && gameState[win[0]]!=2)
            {
                gameOver=true;
                if(gameState[win[0]]==1)
                    winnerID="Player 2";
                LinearLayout layout= (LinearLayout)findViewById(R.id.WinnerLayout);
                TextView winnerMsg= (TextView) findViewById(R.id.winner);
                winnerMsg.setText(winnerID + " has won!!");
                layout.setVisibility(View.VISIBLE);
            }
            else
            {
                gameOver=true;
                for(int i=0;i<gameState.length;i++)
                {
                    if(gameState[i]==2)
                        gameOver=false;
                }
                if(gameOver)
                {
                    LinearLayout layout= (LinearLayout)findViewById(R.id.WinnerLayout);
                    TextView winnerMsg= (TextView) findViewById(R.id.winner);
                    winnerMsg.setText("Its a tie!!");
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        LinearLayout layout= (LinearLayout) findViewById(R.id.WinnerLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        int i;
        for(i=0; i<gameState.length; i++){
            gameState[i]=2;}
        GridLayout gridLayout;
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
