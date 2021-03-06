package com.junenatte.hadoop.union.except;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UnionExceptReducer extends Reducer<Text, Text, Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Text[] fileNames = {new Text("file1.txt"), new Text("file2.txt")};
        boolean flag1, flag2;
        flag1 = flag2 = false;
        for (Text v : values) {
            flag1 = v.equals(fileNames[0]);
            flag2 = v.equals(fileNames[1]);
        }
        if (flag1 && !flag2)
            context.write(key, NullWritable.get());
    }
}
