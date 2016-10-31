package fj.linyu.lucence;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
/**
 * Created by linyu on 2016/9/29.
 */
public class LucenceConfig {

    //索引目录


    private static Directory directory;
    //分词器


    private static Analyzer analyzer;
    // 写索引的配置


    private  static IndexWriterConfig indexWriterConfig;
    //索引目录


    private static String indexPath = "F://lucence";
    static {
        try {
            //文件目录


            if (indexPath.isEmpty()) {
                //内存目录 缺点 断电丢失


                directory = new RAMDirectory();
            } else {
                directory = FSDirectory.open(Paths.get(indexPath));
            }
            //做好的法  内存操作同步到硬盘


            IOContext context=new IOContext();
            directory =  new RAMDirectory(FSDirectory.open(Paths.get(indexPath)),context);

            //分词器可以换


            analyzer = new StandardAnalyzer();
            indexWriterConfig =  new IndexWriterConfig(analyzer);
            // 指定在JVM退出前要执行的代码


            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    LucenceUtil.indexChanged();
                    try {
                        if (null != directory) {
                            directory.close();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Directory getDirectory() {
        return directory;
    }
    public static Analyzer getAnalyzer() {
        return analyzer;
    }
    public static IndexWriterConfig getIndexWriterConfig(){
        return indexWriterConfig;
    }
}
