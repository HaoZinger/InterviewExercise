package com;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

/**
 * 统计文件中单词出现的频次
 */
public class countWordFrequencyInFile {
    public static void main(String[] args) {
        File file = new File("src/file.txt");
        Map<String, Integer> map = countWordFrequency(file);
        map.forEach((x, y) -> System.out.println(String.format(x + " : " + y)));
    }

    private static Map<String, Integer> countWordFrequency(File file) {
        StringBuilder sb = new StringBuilder();
        //        try (Scanner scanner = new Scanner(file)) {
        //            while (scanner.hasNextLine()) {
        //                sb.append(scanner.nextLine());
        //                sb.append(" ");
        //            }
        //        } catch (FileNotFoundException e) {
        //            e.printStackTrace();
        //        }
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append(" ");
            }
            String str = new String(sb);
            str = str.replaceAll("[,.]", "");
            String[] strArr = StringUtils.split(str, " ");
            List<String> strList = Arrays.asList(strArr);
            Map<String, Integer> map = new HashMap<>();
            while (true) {
                map.put(strList.get(0), Collections.frequency(strList, strList.get(0)));
                strList = (List<String>) CollectionUtils.removeAll(strList, Arrays.asList(strList.get(0)));
                if (strList.size() == 0) {
                    break;
                }
            }
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}