import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;

/**
 * @author sofn
 * @version 2016年10月18日 下午 5:31
 */

public class Dictionary  {
    private  String s ;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Test
    public void getArae() throws Exception {
        //项目打包后（jar）有些路径是被隐蔽了，通过this.getClass().getClassLoader().getResource("文件名").getPath();获得文件在打包后项目中的绝度路径
        String fileName = this.getClass().getClassLoader().getResource("CityJson.js").getPath();//获取文件路径
        String fileUtl = this.getClass().getResource("CityJson.js").getFile();
        //Java.net.URL   fileUtl = this.getClass().getResource("");/* .getFile(); */

        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath();//标准的路径 ;
        String author =directory.getAbsolutePath();//绝对路径;
        java.net.URL uri = this.getClass().getResource("/");
        String property =System.getProperty("user.dir");

        String sss=  new File("/").getAbsolutePath();
        // String fileUtl = this.getClass().getResource("CityJson.js").getFile();
        System.out.println("相对路径地址"+fileUtl);
        System.out.println("绝对路径地址"+fileName);
        System.out.println("当前工程路径"+ System.getProperty("java.class.path"));
        System.out.println("当前类的路径"+courseFile);System.out.println("Class文件所在路径"+this.getClass().getResource("/")); // Class文件所在路径
        System.out.println("工程所在盘"+new File("/").getAbsolutePath());
        System.out.println("当前类/当前工程的路径"+System.getProperty("user.dir"));
        System.out.println("获取标准的路径"+directory.getCanonicalPath());//获取标准的路径
        System.out.println("获取绝对路径"+directory.getAbsolutePath());//获取绝对路径

        File  fi =directory.getAbsoluteFile();

        String path = fi.getPath();

        System.out.println("文件路径"+path);

        InputStream inputStream=  this.getClass().getResourceAsStream("CityJson.js");
        InputStreamReader ss = new InputStreamReader(inputStream);

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");

        BufferedReader br = new BufferedReader(inputStreamReader);
        String s = null, ws = null;
        StringBuffer jsonBuffer = new StringBuffer();
        JSONObject jsonObject;
        JSONArray jsonArray;
        //读取到文件内json内容
        while ((s = br.readLine())!= null ){
            jsonBuffer.append(s);
        }
        br.close();
        //因为文件内容直接是一个json数组，就可以直接用JSONArray对象来装
        jsonArray =(JSONArray) JSONArray.parse(jsonBuffer.toString().substring(8,jsonBuffer.length()));
        for (int i=0; i<jsonArray.size(); i++){
            jsonObject =(JSONObject) jsonArray.get(i);
            System.out.print(jsonObject.getString("province"));
            // System.out.print(jsonObject.getString("name"));
            //System.out.print(jsonObject.getString("type"));
        }

    }
}
/*
        String probice,city,distric;
        String probinceID = arae.substring(0,2);
        String cityID = arae.substring(2,4);
        String districtID = arae.substring(4,6);
// File fileName = new File("src/main/java/com/sofn/util/js/CityJson.js");DistrictJson,ProJson
//String fileName = this.getClass().getClassLoader().getResource("ProJson.js").getPath();
String fileName = this.getClass().getClassLoader().getResource("CityJson.js").getPath();
//String fileName = this.getClass().getClassLoader().getResource("DistrictJson.js").getPath();
//String fileNames = this.getClass().getField("CityJson.js").get
        System.out.print("file=:"+fileName);
                * */
