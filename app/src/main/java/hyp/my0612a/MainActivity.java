package hyp.my0612a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 操作文件
 */
public class MainActivity extends AppCompatActivity {
    EditText mEditView;
    Button mButton;
    TextView mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditView = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.write);
        mContent = (TextView) findViewById(R.id.contentValue);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteFiles(mEditView.getText().toString());
                mContent.setText(ReadFiles());
            }
        });
        /**
         * 文件的创建
         */
//        File file = new File("/mnt/sdcard/test0612a");
//        if (file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else{
//            Toast.makeText(this,"文件已存在",Toast.LENGTH_SHORT).show();
//        }

//        file.delete();
//
//        if (file.exists()) {
//            Toast.makeText(this,"文件已存在12",Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this,"文件已删除",Toast.LENGTH_SHORT).show();
//        }
//        File file = this.getFilesDir();//当前应用程序默认的数据存储目录
//        File file = this.getCacheDir();//当前应用程序默认的缓存存放位置
//        File file = this.getDir("fox",MODE_PRIVATE);
//        //等到外部的存储位置  如果app卸载  这里面的数据也会自动清除 （如果开发者不遵守规则  卸载时则会产生垃圾数据）
//        this.getExternalFilesDir(type);
//        this.getExternalCacheDir();
//        Log.i("TAG", file.toString());

    }

    /**
     * 保存文件内容
     */
    public void WriteFiles(String content){
        try {
            FileOutputStream file = openFileOutput("fox.txt",MODE_PRIVATE);
            file.write(content.getBytes());
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     */
    public String ReadFiles(){
        String content = null;
        FileInputStream file = null;
        try {
            file = openFileInput("fox.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = file.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            content = baos.toString();
            file.close();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
