/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionBibliotheque.gestion;

import com.gestionBibliotheque.classe.Eleve;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author solofonirina
 */
public class TraitementFichierExcel extends SwingWorker<Void, Void>{
    GestionEleve ge = new GestionEleve();
    private String path;
    
    public TraitementFichierExcel(String path){
        this.path = path;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        try {
            List<Eleve> eleves = lireExcel(path);
            ge.insertExcel(eleves);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, "Insertion élève complète!");
    }
    
    public List<Eleve> lireExcel(String fichier){
        InputStream pythonStream = getClass().getResourceAsStream("/com/gestionBibliotheque/gestion/readFileExcel.py");
        if (pythonStream == null) {
            throw new IllegalStateException("Le script Python n'a pas été trouvé.");
        }

        File tempScript = null;
        try {
            tempScript = File.createTempFile("readFileExcel", ".py");
            try (FileOutputStream out = new FileOutputStream(tempScript)) {
                IOUtils.copy(pythonStream, out);
            }

            String fileExcel = new File(fichier).getAbsolutePath();
            List<Eleve> ls = new ArrayList<>();

            String command = "python3 " + tempScript.getAbsolutePath() + " " + fileExcel;
//            System.out.println(command);
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parties = line.split(":");
                    if(parties.length < 2) {
                        System.err.println("Format de ligne incorrect : " + line);
                        continue;  // saute cette ligne et continue avec la suivante
                    }
                    ls.add(new Eleve(parties[0].trim(), parties[1].trim()));
                }

            }

            process.destroy();
            return ls;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (tempScript != null) {
                tempScript.delete();
            }
        }
    }

}
