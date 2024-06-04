package org.chat.investpro;

import lombok.Data;
import java.util.ArrayList;

@Data
public class Server {

    private CsvReader reader = new CsvReader();
    private CsvWriter writer = new CsvWriter();
    private CsvEditor editor = new CsvEditor();

}
