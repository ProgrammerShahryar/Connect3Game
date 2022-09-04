package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActice = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActice == true) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winPos : winningPositions) {
                if (gameState[winPos[0]] == gameState[winPos[1]] &&
                        gameState[winPos[1]] == gameState[winPos[2]] &&
                        gameState[winPos[0]] != 2) {
                    gameActice = false;
                    String winnerMessage = "";
                    if (activePlayer == 1) {
                        winnerMessage = "Yellow";
                    } else {
                        winnerMessage = "Red";
                    }
                    TextView textView = findViewById(R.id.winnerTextView);
                    textView.setText(winnerMessage + " has won!");
                    Button button = findViewById(R.id.playAgainBtn);
                    button.setVisibility(View.VISIBLE);

                    }

                }

        }

    }

    public void PlayAgain(View view) {
        TextView textView = findViewById(R.id.winnerTextView);
        Button button = findViewById(R.id.playAgainBtn);

        textView.setText("");
        button.setVisibility(View.INVISIBLE);

        gameActice = true;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        TableLayout tableLayout = findViewById(R.id.TableLayout);

        for (int r = 0; r < tableLayout.getChildCount(); r++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(r);
            for (int c = 0; c < tableRow.getChildCount(); c++) {

                ImageView imageView = (ImageView) tableRow.getChildAt(c);
                imageView.setImageDrawable(null);
            }
        }

    }
}