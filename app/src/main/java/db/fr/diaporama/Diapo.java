package db.fr.diaporama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.*;

public class Diapo extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageViewDiapo;

    private TextView textViewCountImg;
    private TextView textViewNumImg;

    private Button buttonFirst;
    private Button buttonLast;
    private Button buttonNext;
    private Button buttonPrev;

    private List<String> filesName;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diapo);

        imageViewDiapo = (ImageView) findViewById(R.id.imageViewDiapo);
        buttonFirst = (Button) findViewById(R.id.buttonFirst);
        buttonLast = (Button) findViewById(R.id.buttonLast);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPrev = (Button) findViewById(R.id.buttonPrev);

        textViewCountImg = (TextView) findViewById(R.id.textViewCountImg);
        textViewNumImg = (TextView)findViewById(R.id.textViewNumImg);

        filesName = new ArrayList();
        File dir = this.getFilesDir();
        String[] tFiles = dir.list();

        for (int i = 0; i < tFiles.length; i++) {
            if (tFiles[i].endsWith(".jpg") || tFiles[i].endsWith(".png")) {
                filesName.add(tFiles[i]);
            }
        }

        textViewCountImg.setText(String.valueOf(filesName.size()));
        textViewNumImg.setText(String.valueOf(index));

        buttonFirst.setOnClickListener(this);
        buttonLast.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonPrev.setOnClickListener(this);

        viewImage();
    }

    /**
     * Afficher l'image dans ImageView
     */
    private void viewImage() {
        textViewNumImg.setText(String.valueOf(index+1));

        buttonFirst.setEnabled(true);
        buttonPrev.setEnabled(true);
        buttonFirst.setBackgroundColor(Color.BLACK);
        buttonPrev.setBackgroundColor(Color.BLACK);
        buttonLast.setEnabled(true);
        buttonNext.setEnabled(true);
        buttonLast.setBackgroundColor(Color.BLACK);
        buttonNext.setBackgroundColor(Color.BLACK);

        if (index == 0) {
            buttonFirst.setEnabled(false);
            buttonPrev.setEnabled(false);
            buttonFirst.setBackgroundColor(Color.RED);
            buttonPrev.setBackgroundColor(Color.RED);
        }
        if (index == filesName.size() - 1) {
            buttonLast.setEnabled(false);
            buttonNext.setEnabled(false);
            buttonLast.setBackgroundColor(Color.RED);
            buttonNext.setBackgroundColor(Color.RED);
        }
        try {
            String lsChemin = this.getFilesDir().getAbsolutePath() + "/" + filesName.get(index);
            InputStream is = new FileInputStream(lsChemin);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageViewDiapo.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext) {
            index += 1;
        }
        if (v == buttonPrev) {
            index -= 1;
        }
        if (v == buttonFirst) {
            index = 0;
        }
        if (v == buttonLast) {
            index = filesName.size() - 1;
        }
        viewImage();
//        if (v==buttonFirst || v==buttonLast) {
//            textViewNumImg.setText(String.valueOf(index + 1));
//        } else {
//            textViewNumImg.setText(String.valueOf(index));
//        }
    }


    private void prevButton() {

    }

    private void nextButton() {
    }

    private Boolean isImageFile(String psPathFile) {
        Boolean isImage = false;
        String[] ext = {".jpg", ".bmp"};

        isImage = psPathFile.endsWith(".jpg");

//        int i = 0;
//        while (isImage != true && i < ext.length) {
//            if (ext[i].equals(extTab[1])) {
//                isImage = true;
//            }
//            i = i + 1;
//        }

        return isImage;
    }
}
