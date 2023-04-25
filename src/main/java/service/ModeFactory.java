package service;

import Utils.Export;
import Utils.RegisteredFlag;
import Utils.RegisteredOption;
import domain.Consult;
import domain.ModeStrategy;

public class ModeFactory {

    public static ModeStrategy get(ArgumentParser argParser)
    {
        String table = argParser.getOption(RegisteredOption.TABELA);

        if(table.isEmpty())
            throw new IllegalArgumentException("A option 'TABELA' não pode estar vazia");


        if(argParser.getFlag(RegisteredFlag.CONSULT))
        {

            String filter = argParser.getOption(RegisteredOption.FILTRO);
            if(filter.isEmpty())
                throw new IllegalArgumentException("Para a flag 'CONSULT' a option 'FILTRO' não pode estar vazia");

            String collumn = String.join(",", argParser.getOption(RegisteredOption.COLUNA).split(" "));
            if(collumn.isEmpty())
                throw new IllegalArgumentException("Para a flag 'CONSULT' a option 'COLLUMN' não pode estar vazio");

            String export = argParser.getOption(RegisteredOption.EXPORT);

            if(export.isEmpty() || export.equals("off"))
            {
                return new Consult(table, filter, collumn, Export.OFF);
            }

            return new Consult(table, filter, collumn, Export.ON);

        }

        return null;
    }
}
