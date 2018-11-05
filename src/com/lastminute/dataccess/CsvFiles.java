package com.lastminute.dataccess;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public final class CsvFiles
{
  private CsvFiles()
  {
  }

  /**
   * Read all the records of the provided file
   * @param String fileName The name of the file you want to read from
   * @return List records The list of records read from the file
   */
  public static List<List<String>> readAllRecords(String fileName)
  {
    try (Stream<List<String>> records = records(fileName))
    {
      return records.collect(toList());
    }
  }

  /**
   * Read the records of the provided file
   * @param String fileName The name of the file you want to read from
   * @return Stream the splitted records read from the file
   */
  public static Stream<List<String>> records(String fileName)
  {
    return splitByCommaIgnoringHeader(linesOf(fileName));
  }

  private static Stream<List<String>> splitByCommaIgnoringHeader(Stream<String> lines)
  {
    return lines.skip(1)
                .map(line -> line.split(","))
                .map(Arrays::asList);
  }

  private static Stream<String> linesOf(String fileName)
  {
    try
    {
      return Files.lines(Paths.get(fileName));
    }
    catch (IOException e)
    {
      throw new UncheckedIOException("IO error accessing the CSV file " + fileName, e);
    }
  }
}
