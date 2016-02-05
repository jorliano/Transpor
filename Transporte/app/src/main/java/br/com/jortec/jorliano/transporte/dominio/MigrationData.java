package br.com.jortec.jorliano.transporte.dominio;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.internal.Table;

/**
 * Created by Jorliano on 24/01/2016.
 */
public class MigrationData implements RealmMigration {

    public static final int VERSION = 1;


    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long newVersion) {

      /*  if( l == VERSION - 1 ) {
            Table estudenteTable = dynamicRealm.getTable(Servicos.class);
            long idKey = estudenteTable.getColumnIndex("id");
            estudenteTable.addSearchIndex(idKey);

            Table disciplinaTable = realm.getTable(Transporte.class);
            idKey = disciplinaTable.getColumnIndex("id");
            disciplinaTable.addSearchIndex(idKey);

            Table notaTable = realm.getTable(Material.class);
            idKey = notaTable.getColumnIndex("id");
            notaTable.addSearchIndex(idKey);
        }*/
    }
}
