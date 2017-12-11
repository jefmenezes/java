/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.remote.JMXProviderException;
import javax.swing.JOptionPane;

/**
 *
 * @author jeferson
 */
public class Pdf {
    
    private File file;
    private FileOutputStream fos;
    private Rectangle pageSize = PageSize.A4;
    private float leftMagin = 30;
    private float rigthMargin = 30;
    private float topMargin = 15;
    private float bottonMargin = 15;
    private int fs = 8;
    private PdfWriter writer;
    private String docTitle;
    private Document doc = new Document(this.pageSize, leftMagin, rigthMargin, topMargin, bottonMargin);
    private BaseFont arial;
    private BaseFont arialB;
    private Font fonte;
    private Font fonteB;
    private Font fonteRB;
    private Font fonte5;
    private Font fonte6;
    private ArrayList<Paragraph> paragrafos = new ArrayList<Paragraph>();
    private ArrayList<PdfPTable> tabelas = new ArrayList<PdfPTable>();
    private PdfPTable currentTable;
    private PdfPTable cabecalho;
    private PdfPTable sabados;
    private PdfPTable legenda;
    private PdfPTable obs;
    private int numColumns = 0;
    private int midcolor = 50*50*50;
    private int tp = 704;
    private int ed = 85;
    
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public Pdf(File file) {
//        try {
//            arial = BaseFont.createFont("arial.ttf", BaseFont.WINANSI, true);
//            arialB = BaseFont.createFont("arialbd.ttf", BaseFont.WINANSI, true);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        fonte = new Font(arial, fs, Font.NORMAL);
//        fonte6 = new Font(arial, 6, Font.NORMAL);
//        fonte5 = new Font(arial, 5, Font.NORMAL);
//        fonteB = new Font(arialB, fs, Font.NORMAL);
//        fonteRB = new Font(arialB, fs, Font.NORMAL, BaseColor.RED);
        try {
            this.file = file;
            fos = new FileOutputStream(file);
            writer = PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.addTitle("teste");
            doc.setPageSize(PageSize.A4);
            //doc.setMargins(10, 10,);
            Image img = Image.getInstance("marca.png");
            img.scaleToFit(PageSize.A4);
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);
            String txt = "Jeferson Menezes dos Santos Silva";
            if(txt.charAt(27)==' ')
                txt = txt.substring(0, 27)+txt.substring(28);
            char[] teste = txt.toCharArray();
            int x = ed;
            int y = tp;
            for(char c : teste){
                absText(""+c, x, y);
                x+=16;
                if(x>510){
                    x=ed;
                    y=tp-16;
                }
            }
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Pdf(ArrayList<Aluno> alns, File file) {
        int posx = 378;
        int posy = 657;
        try {
            this.file = file;
            fos = new FileOutputStream(file);
            writer = PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.addTitle("teste");
            doc.setPageSize(PageSize.A4);
            
            Image img = Image.getInstance("marca.png");
            img.scaleToFit(PageSize.A4);
            img.setAlignment(Element.ALIGN_CENTER);
            Image mk = Image.getInstance("mk.png");
            mk.setAlignment(Element.ALIGN_CENTER);
            mk.scaleAbsolute(12f, 12f);
            Collections.sort(alns);
            for(Aluno a : alns){
                doc.setMargins(0, 0, 0, 0);
                doc.add(img);
                String txt = a.getNome();
                if (txt.length()>27 && txt.charAt(27) == ' ') {
                    txt = txt.substring(0, 27) + txt.substring(28);
                }
                char[] teste = txt.toCharArray();
                int x = ed;
                int y = tp;
                for (char c : teste) {
                    absText("" + c, x, y);
                    x += 16;
                    if (x > 510) {
                        x = ed;
                        y = tp-16;
                    }
                }
                String cod = ""+a.getCod();
                if(cod.length()<6)
                    cod = "0"+cod;
                char[] cc = cod.toCharArray();
                int i = 0;
                for(char c : cc){
                     int v = Integer.parseInt(""+c);
                     absText(""+c, 366, 659-i*14);
                     //mk.setAbsolutePosition(388, 628);
                     mk.setAbsolutePosition(posx+v*13.7f, posy-i*13.7f);
                     doc.add(mk);
                     i++;
                }
                 doc.newPage();
            }
           
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Pdf(ArrayList<Aluno> alns){
        this.alunos = alns;
        
    }
    
    public void gerarRelatorio(File file) {
       // doc = new Document(this.pageSize, leftMagin, rigthMargin, topMargin, bottonMargin);
        doc.newPage();
        try {
            fos = new FileOutputStream(file);
            writer = PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.addTitle("Relatório de Notas");
            doc.setPageSize(PageSize.A4);
           // createCabecalho("", "", "", "");
            addResultTable();
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void absText(String text, float x, float y) {
    try {
      PdfContentByte cb = writer.getDirectContent();
      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
      cb.saveState();
      cb.beginText();
      cb.moveText(x, y);
      cb.setFontAndSize(bf, 14);
      cb.showText(text);
      cb.endText();
      cb.restoreState();
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
    
    private Font getFont(int size, Color cor){
        return new Font(arial, size, Font.NORMAL, new BaseColor(cor.getRGB()));
    }

    public void createCabecalho(String escola, String materia, String professor, String turma){
        cabecalho = new PdfPTable(1);
        cabecalho.setWidthPercentage(100f);
        addCabecalho("CENTRO DE EXCELÊNCIA SANTOS DUMONT", new BaseColor(205, 255, 204));
        addCabecalho("ENSINO MÉDIO INTEGRAL", new BaseColor(153, 204, 255));
        addCabecalho("NOTAS DO III SIMULADO - 2017", new BaseColor(255, 204, 153));
        try {
            doc.add(cabecalho);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        Chunk c = new Chunk(" ", fonte5);
        Paragraph parag = new Paragraph(c);
        paragrafos.add(parag);
        try {
            doc.add(parag);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addCabecalho(String txt, BaseColor cor){
        Phrase p = new Phrase(new Chunk(txt, fonteB));
        PdfPCell cell = new PdfPCell(p);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(cor);
        cabecalho.addCell(cell);
    }

    public boolean addPage(){
        return doc.newPage();
    }

    public void addParagrafo(String texto, int alinhamento) {
        try {
            Chunk c = new Chunk(texto, fonte);
            //c.setBackground(new BaseColor(204, 255, 204));
            Paragraph parag = new Paragraph(c);
            paragrafos.add(parag);
            parag.setAlignment(alinhamento);
            doc.add(parag);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable(int columns) {
        try {
            this.numColumns = columns;
            PdfPTable table = new PdfPTable(columns);
            tabelas.add(table);
            table.setWidthPercentage(100f);
            int[] widths = {3, 50, 8, 8, 8, 14};
            table.setWidths(widths);
            currentTable = table;
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addResultTable(){
        DecimalFormat df2 = new DecimalFormat("##.#");
        PdfPTable table = new PdfPTable(5);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100f);
        int[] widths = {5,65,10,10,10};
        try {
            table.setWidths(widths);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = 0;
        for (Aluno a : alunos) {
            Respostas resp = a.getRespostas();
            if (resp != null) {
                n++;
                Phrase p = new Phrase("" + n);
                PdfPCell nc = new PdfPCell(p);
                table.addCell(nc);
                PdfPCell namec = new PdfPCell(new Phrase(a.getNome()));
                table.addCell(namec);
                HashMap<Area, ArrayList<Questao>> questoes = new HashMap<>();

                for (Questao q : resp.getQuestoes()) {
                    Area area = q.getDisciplina().getArea();
                    ArrayList<Questao> qst = questoes.get(area);
                    if (qst == null) {
                        qst = new ArrayList<>();
                        questoes.put(area, qst);
                    }
                    qst.add(q);
                }
                Set<Area> areas = questoes.keySet();
                //            int[] qa = new int[areas.size()];
                //            int i = 0;
                for (Area area : areas) {
                    int nq = questoes.get(area).size();
                    double vq = 10d / nq;
                    System.out.println("Valor Questão: "+vq);
                    double tot = 0;
                    for (Questao q : questoes.get(area)) {
                        if (q.isCerto()) {
                            tot += vq;
                        }
                    }
                    BigDecimal r = new BigDecimal(tot);
                    
                    r = r.round(new MathContext(2, RoundingMode.HALF_UP));
                    PdfPCell areac = new PdfPCell(new Phrase("" + r.doubleValue()));
                    table.addCell(areac);
                }
            }
            table.completeRow();
        }
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void createTable(Dados dados) {
//        PdfPTable table = new PdfPTable(5);
//        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//        table.setWidthPercentage(100f);
//        int[] widths = {32, 2, 32, 2, 32};
//        try {
//            table.setWidths(widths);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        int col = 1;
//        int totlet = 0;
//        ArrayList<Mes> meses = dados.getCalendario().getMeses();
//        
//        for (Mes m : meses) {
//            PdfPTable mpt = new PdfPTable(7);
//            Phrase p = null;
//            if(totlet<200)
//                p = new Phrase(new Chunk(m.toString().toUpperCase()+"  -  Dias Letivos: "+dados.getCalendario().getDiasLetivosMes(m).size(), fonteB));
//            else
//                p = new Phrase(new Chunk(m.toString().toUpperCase()+"  -  Dias Letivos: 0", fonteB));
//            totlet += m.getDiasLetivos().size();
//            PdfPCell cell = new PdfPCell(p);
//            cell.setColspan(7);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBackgroundColor(new BaseColor(255, 204, 153));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("D", fonteRB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("S", fonteB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("T", fonteB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("Q", fonteB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("Q", fonteB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("S", fonteB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            p = new Phrase(new Chunk("S", fonteB));
//            cell = new PdfPCell(p);
//            cell.setColspan(1);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            //cell.setBackgroundColor(new BaseColor(0, 0, 255));
//            mpt.addCell(cell);
//            Dia[][] dias = m.getDias();
//            for(int i=0; i<dias.length; i++){
//                for(int j=0; j<7; j++){
//                    p = new Phrase(new Chunk(" ", fonte));
//                    //Evento evt = null;
//                    ArrayList<Evento> evts = new ArrayList<>();
//                    BaseColor bgc = new BaseColor(255, 255, 255);
//                    //float[] hsb = new float[3];
//                    if(dias[i][j]!=null){
//                        //evt = dias[i][j].getEvento();
//                        evts = dias[i][j].getEventos();//dados.getMeses()[n].getDias().get(i).getEventos();
//                        Color fc = Color.BLACK;
//                        if(evts!=null && evts.size()>0)
//                            if(evts.size()<2)
//                                fc = evts.get(0).getFontColor();
//                        p = new Phrase(new Chunk(" "+dias[i][j].getValue(), getFont(fs, fc)));
//                    }
//                    cell = new PdfPCell(p);
//                    cell.setBackgroundColor(bgc);
//                    cell.setColspan(1);
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    
//                    cell.setPhrase(p);
//                    EventoPdfPCellEvent ce = new EventoPdfPCellEvent(evts);
//                    cell.setCellEvent(ce);
//                    mpt.addCell(cell);
//                }
//            }
//            mpt.completeRow();
//            PdfPCell filha = new PdfPCell(mpt);
//            //filha.setBorder(PdfPCell.NO_BORDER);
//            filha.setColspan(1);
//            filha.setHorizontalAlignment(Element.ALIGN_CENTER);
//            filha.setVerticalAlignment(Element.ALIGN_CENTER);
//            table.addCell(filha);
//            p = new Phrase(new Chunk(" ", fonte));
//            filha = new PdfPCell(p);
//            filha.setBorder(PdfPCell.NO_BORDER);
//            if(!(col%3==0)){
//                table.addCell(filha);
//            }else{
//                table.addCell(filha);
//                table.addCell(filha);
//                table.addCell(filha);
//                table.addCell(filha);
//                table.addCell(filha);
//            }
//            col++;
//        }
//        try {
//            table.completeRow();
//            doc.add(table);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        addParagrafo(" ", Element.ALIGN_CENTER);
//    }
//    
//    public void addLegenda(ArrayList<Evento> evts){
//        legenda = new PdfPTable(6);
//        legenda.setWidthPercentage(100f);
//        legenda.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//        int[] widths = {5, 28, 5, 28, 5, 29};
//        try {
//            legenda.setWidths(widths);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Phrase p = new Phrase(new Chunk("LEGENDA", fonteB));
//        PdfPCell cell = new PdfPCell(p);
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setColspan(6);
//        legenda.addCell(cell);
//        int inc = 0;
//        if(evts.size()%3==0)
//            inc = evts.size()/3;
//        else
//            inc = evts.size()/3 + 1;
//        legenda.completeRow();
//        for(int i=0; i<inc; i++){
//            for(int j=i; j<evts.size(); j+=inc){
//                Evento e = evts.get(j);
//                p = new Phrase(new Chunk("", fonte));
//                cell = new PdfPCell(p);
//                cell.setBorder(PdfPCell.BOX);
//                cell.setBackgroundColor(new BaseColor(e.getCor().getRGB()));
//                legenda.addCell(cell);
//                p = new Phrase(new Chunk(e.getEvt(), fonte));
//                cell = new PdfPCell(p);
//                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                legenda.addCell(cell);
//            }
//        }
//        legenda.completeRow();
//        try {
//            doc.add(legenda);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        addParagrafo("", Element.ALIGN_CENTER);
//    }
//    
//    public void addSabados(Dados dados){
//        int nc = 17;
//        sabados = new PdfPTable(nc);
//        sabados.setWidthPercentage(100f);
//        sabados.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//        Phrase p = new Phrase(new Chunk("SÁBADOS LETIVOS", fonteB));
//        PdfPCell cell = new PdfPCell(p);
//        cell.setBorder(PdfPCell.NO_BORDER);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setColspan(17);
//        sabados.addCell(cell);
////        p = new Phrase(new Chunk("Data", fonte));
////        cell = new PdfPCell(p);
////        cell.setBorder(PdfPCell.BOX);
////        sabados.addCell(cell);
//        ArrayList<Dia> sbds = dados.getCalendario().getSabadosLetivos();
////        int nr = sbds.size()/16;
////        if(sbds.size()%16==0)
////            nr++;
////        for(Dia d: sbds){
////            String ano = (""+d.getAno()).substring(2);
////                p = new Phrase(new Chunk(""+d.getValue()+"/"+(d.getMes()+1)+"/"+ano, getFont(7, Color.BLACK)));
////                cell = new PdfPCell(p);
////                cell.setBorder(PdfPCell.BOX);
////                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                sabados.addCell(cell);
////        }
//        String[][] dd = new String[4][17];
//        int nr = 0;
//        for (Dia d : sbds) {
//            if(nr%(nc-1)==0){
//                p = new Phrase(new Chunk("Data", fonte));
//                cell = new PdfPCell(p);
//                cell.setBorder(PdfPCell.BOX);
//                cell.setBackgroundColor(BaseColor.GRAY.brighter());
//                sabados.addCell(cell);
//            }
//            String ano = ("" + d.getAno()).substring(2);
//            p = new Phrase(new Chunk("" + d.getValue() + "/" + (d.getMes() + 1) + "/" + ano, getFont(7, Color.BLACK)));
//            cell = new PdfPCell(p);
//            cell.setBorder(PdfPCell.BOX);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBackgroundColor(BaseColor.GRAY.brighter());
//            sabados.addCell(cell);
//            nr++;
//        }
//        sabados.completeRow();
//        nr=0;
//        for(Dia d : sbds){
//            if(nr%(nc-1)==0){
//                p = new Phrase(new Chunk("Dia", fonte));
//                cell = new PdfPCell(p);
//                cell.setBorder(PdfPCell.BOX);cell.setBackgroundColor(BaseColor.GRAY.brighter().brighter());
//                sabados.addCell(cell);
//            }
//            p = new Phrase(new Chunk(getAbrDay(d.getRefDay()), fonte));
//            cell = new PdfPCell(p);
//            cell.setBorder(PdfPCell.BOX);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setBackgroundColor(BaseColor.GRAY.brighter().brighter());
//            sabados.addCell(cell);
//            nr++;
//        }
//        
//        sabados.completeRow();
////        p = new Phrase(new Chunk("Dia", fonte));
////        cell = new PdfPCell(p);
////        cell.setBorder(PdfPCell.BOX);
////        sabados.addCell(cell);
////        for(Dia d : sbds){
////            p = new Phrase(new Chunk(getAbrDay(d.getRefDay()), fonte));
////            cell = new PdfPCell(p);
////            cell.setBorder(PdfPCell.BOX);
////            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////            sabados.addCell(cell);
////        }
//        try {
//            doc.add(sabados);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        addParagrafo("", Element.ALIGN_CENTER);
//    }
    
    public String getAbrDay(int day){
        switch(day){
            case 1: return "Dom";
            case 2: return "Seg";
            case 3: return "Ter";
            case 4: return "Qua";
            case 5: return "Qui";
            case 6: return "Sex";
            case 7: return "Sab";
        }
        return "";
    }
    
    public void addObservacoes(ArrayList<String> o){
        obs = new PdfPTable(1);
        obs.setWidthPercentage(100f);
        for(String s : o){
            addObservacoes(s, BaseColor.WHITE);
        }
        try {
            doc.add(obs);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addObservacoes(String txt, BaseColor cor){
        Phrase p = new Phrase(new Chunk(txt, fonte));
        PdfPCell cell = new PdfPCell(p);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(cor);
        obs.addCell(cell);
    }

    public void addCelula(String texto, int vAlign, int hAlign, BaseColor bgColor, int border){
        if (currentTable == null) {
            return;
        }
        Phrase p = new Phrase(new Chunk(texto, fonte));
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(hAlign);
        cell.setVerticalAlignment(vAlign);
        cell.setBackgroundColor(bgColor);
        cell.setBorder(border);
        currentTable.addCell(cell);
    }

    public void addTableRow(String[] textos, int[] vAlign, int[] hAlign, BaseColor[] bgColor, int[] border, Font[] font ){
        for(int i=0; i<numColumns; i++){
            this.fonte = font[i];
            addCelula(textos[i], vAlign[i], hAlign[i], bgColor[i], border[i]);
        }
    }

    public void setColumnsWidths(int[] widths){
        try {
            currentTable.setWidths(widths);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTable(){
        try {
            doc.add(currentTable);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close(){
        doc.close();

    }

    public void setFontStyle(int style){
        this.fonte.setStyle(style);
    }

    public void setFontSize(float size){
        this.fonte.setSize(size);
    }
    
//    public void gerarAulas(Dados dados, ArrayList<Turma> turmas){
////        for(Turma t : turmas){
////            try {
////                for (Disciplina d : t.getDisciplinas()) {
////                    int tot = 0;
////                    addParagrafo("" + t.getSerie() + t.getTurma(), Element.ALIGN_LEFT);
////                    PdfPTable table = new PdfPTable(35);
////                    //table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
////                    table.setWidthPercentage(100f);
//////                  int[] widths = {32, 2, 32, 2, 32};
//////                  table.setWidths(widths);
////                    Phrase p = new Phrase(new Chunk(d.getNome().toUpperCase(), fonteB));
////                    PdfPCell cell = new PdfPCell(p);
////                    cell.setColspan(35);
////                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    cell.setBackgroundColor(new BaseColor(255, 204, 153));
////                    table.addCell(cell);
////                    for(int i=0; i<30; i++){
////                        if(i==0){
////                            p = new Phrase(new Chunk("", fonteB));
////                            cell = new PdfPCell(p);
////                            cell.setColspan(6);
////                            table.addCell(cell);
////                        }else{
////                            p = new Phrase(new Chunk(""+i, fonteB));
////                            cell = new PdfPCell(p);
////                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                            table.addCell(cell);
////                        }
////                    }
////                    int m = -1;
////                    for(Dia dia : dados.getCalendario().getDiasLetivos()){
////                        if(dia.getMes()!=m){
////                            table.completeRow();
////                            m = dia.getMes();
////                            p = new Phrase(new Chunk(Mes.getMesName(dia.getMes()).toUpperCase()+" "+dia.getAno(), fonte));
////                            cell = new PdfPCell(p);
////                            cell.setColspan(6);
////                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
////                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                            table.addCell(cell);
////                        }
////                            for(Dia dia: d.){
////                                if(dia.getRefDay()==i){
////                                    //System.out.println(""+i);
////                                    p = new Phrase(new Chunk(""+dia.getValue(), fonte));
////                                    cell = new PdfPCell(p);
////                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////                                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                                    //cell.setBackgroundColor(new BaseColor(255, 204, 153));
////                                    table.addCell(cell);
////                                    tot++;
////                                    //addParagrafo(""+dia.getValue()+"/"+dia.getMes()+"/"+dia.getAno(), Element.ALIGN_LEFT);
////                                }
////                            }
////                        }
//////                    }
////                    table.completeRow();
////                    p = new Phrase(new Chunk("Total:", fonte));
////                    cell = new PdfPCell(p);
////                    cell.setColspan(6);
////                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
////                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    table.addCell(cell);
////                    p = new Phrase(new Chunk(""+tot, fonte));
////                    cell = new PdfPCell(p);
////                    cell.setColspan(29);
////                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
////                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    table.addCell(cell);
////                    
////                    doc.add(table);
////                }
////                
////                addParagrafo("", Element.ALIGN_LEFT);
////            } catch (DocumentException ex) {
////                Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////        //close();
//    }
//    
//    public void gerarAulas(Dados dados){
//        ArrayList<Dia> inihor = dados.getDataHorarios();
//        Collections.sort(inihor);
//        ArrayList<Dia> dias = dados.getCalendario().getDiasLetivos();
//        int tablecont = 0;
//        for(Turma t : dados.getTurmas()){
//            try {
//                for (Disciplina d : t.getDisciplinas()) {
//                    int tot = 0;
//                    addParagrafo(" ", Element.ALIGN_LEFT);
//                    PdfPTable table = new PdfPTable(35);
//                    //table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//                    table.setWidthPercentage(100f);
////                  int[] widths = {32, 2, 32, 2, 32};
////                  table.setWidths(widths);
//                    Phrase p = new Phrase(new Chunk("" + t.getSerie() + t.getTurma()+" - "+d.getNome().toUpperCase(), fonteB));
//                    PdfPCell cell = new PdfPCell(p);
//                    cell.setColspan(35);
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell.setBackgroundColor(new BaseColor(255, 204, 153));
//                    table.addCell(cell);
//                    for(int i=0; i<30; i++){
//                        if(i==0){
//                            p = new Phrase(new Chunk("", fonteB));
//                            cell = new PdfPCell(p);
//                            cell.setColspan(6);
//                            table.addCell(cell);
//                        }else{
//                            p = new Phrase(new Chunk(""+i, fonteB));
//                            cell = new PdfPCell(p);
//                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                            table.addCell(cell);
//                        }
//                    }
//                    int m = -1;
//                    for(Dia dia : d.getDiario().getAulas(dias)){
//                        if(dia.getMes()!=m){
//                            table.completeRow();
//                            m = dia.getMes();
//                            p = new Phrase(new Chunk(Mes.getMesName(dia.getMes()).toUpperCase()+" "+dia.getAno(), fonte));
//                            cell = new PdfPCell(p);
//                            cell.setColspan(6);
//                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                            table.addCell(cell);
//                        }
//                        p = new Phrase(new Chunk("" + dia.getValue(), fonte));
//                        cell = new PdfPCell(p);
//                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                        table.addCell(cell);
//                        tot++;
////                        for (int i : d.getDiario().getDias()) {
////                            if (dia.getRefDay() == i) {
////                                p = new Phrase(new Chunk("" + dia.getValue(), fonte));
////                                cell = new PdfPCell(p);
////                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
////                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                                table.addCell(cell);
////                                tot++;
////                            }
////                        }
//                    }
//                    table.completeRow();
//                    p = new Phrase(new Chunk("Total:", fonte));
//                    cell = new PdfPCell(p);
//                    cell.setColspan(6);
//                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    table.addCell(cell);
//                    p = new Phrase(new Chunk(""+tot, fonte));
//                    cell = new PdfPCell(p);
//                    cell.setColspan(29);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    table.addCell(cell);
//                    
//                    doc.add(table);
//                    tablecont++;
//                    if(tablecont==4){
//                        tablecont=0;
//                        doc.newPage();
//                        //addParagrafo(" ", Element.ALIGN_LEFT);
//                    }
//                }
//                
//                addParagrafo("", Element.ALIGN_LEFT);
//            } catch (DocumentException ex) {
//                Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        //close();
//    }


}

