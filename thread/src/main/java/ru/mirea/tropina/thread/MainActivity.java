package ru.mirea.tropina.thread;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.tropina.thread.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private	int	counter	=	0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView infoTextView = findViewById(R.id.textView);
        TextView numberText = findViewById(R.id.textViewMirea2);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 7, НОМЕР ПО СПИСКУ: 22, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Богемская Рапсодия");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));
        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public void onClick(View view)	{
                long endTime	=	System.currentTimeMillis()	+	20	*	10;
                while	(System.currentTimeMillis()	<	endTime)	{
                    synchronized	(this)	{
                        try	{
                            wait(endTime	- System.currentTimeMillis());
                            float number=68/25f;
                            numberText.setText("Среднее количсетво пар в день "+Math.round(number) );
                        }	catch	(Exception	e)	{
                            throw	new	RuntimeException(e);
                        }
                    }
                }
            }
        });
        binding.button.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{
                new	Thread(new	Runnable()	{
                    public	void run()	{
                        int	numberThread	=	counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток	№	%d	студентом	группы	№	%s	номер	по списку	№	%d	",	numberThread,	"БСБО-07-21",	5));
                        long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
                        while	(System.currentTimeMillis()	<	endTime)	{
                            synchronized	(this)	{
                                try	{
                                    wait(endTime	- System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),	"Endtime:	"	+	endTime);
                                }	catch	(Exception	e)	{
                                    throw	new	RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject",	"Выполнен поток №	"	+	numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}