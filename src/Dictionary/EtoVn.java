//get data by offset and length in anhviet109K.index
package Dictionary;

import java.io.RandomAccessFile;

public class EtoVn {
    /**
     * dataPath: duong dan cua file data.
     * Slength: do dai cua tu trong file .dict, dang decimal 64bit
     * Sposition: vi tri cua tu trong file .dict, dang decimal 64bit
     *
     */

    private  String dataPath = "";
    private  String Slength = "";
    private  String Sposition = "";
    public EtoVn(){}
    public void setEtoVn(String s, String p) {
        Slength = p;
        Sposition = s;
    }
    public EtoVn(String dataPath){
        this.dataPath = dataPath;
    }

    /**
     * chuyen dang decimal 64bit qua int
     */
    public int convertToDecimal(String contents) {
        int result = 0;
        String Sixty_bit = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        for(int i = 0 ;i < contents.length();i++) {
            for(int j = 0; j < Sixty_bit.length();j++) {
                if(contents.charAt(i) == Sixty_bit.charAt(j)) {
                    result = result + j*(int)Math.pow(64,Math.abs(contents.length()-1-i));
                }
            }
        }
        return result;
    }

    /**
     * Truy cap vao file theo dataPath, lay va tra ve du lieu
     * @return
     */
    public String getWordMeaning() {
        if(Slength != "" && Sposition != "") {
            int length = convertToDecimal(Slength);
            int position = convertToDecimal(Sposition);

            byte[] magic = new byte[length];
            try {
                RandomAccessFile raf = new RandomAccessFile(dataPath, "rw");
                try {
                    raf.seek(position);
                    raf.readFully(magic);
                    // System.out.println(new String(magic));

                } catch (Exception e) {
                    System.out.println(e);
                }
                raf.close();
            }
            catch (Exception e){
                System.out.println(e);
            }

            String m = new String(magic);

            return m;
        }
        else return null;
    }
}
