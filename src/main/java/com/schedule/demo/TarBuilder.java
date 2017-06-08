package com.schedule.demo;

public class TarBuilder {
	// private Logger logger = Logger.getLogger(getClass().getName());
	// private TarArchiveOutputStream tarArchiveOutputStream;
	// private File source;
	// private File dist;
	// private boolean deleteSource;
	//
	// public TarBuilder(String srcDir,String distDir,String tarName,boolean
	// deleteSource){
	// source=new File(srcDir);
	// dist = new File(distDir, tarName);
	// this.deleteSource=deleteSource;
	// if (source.exists()) {
	// try {
	// tarArchiveOutputStream = new TarArchiveOutputStream(new
	// FileOutputStream(dist));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// public void build() {
	// action(source);
	// if (tarArchiveOutputStream != null) {
	// try {
	// tarArchiveOutputStream.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// private void action(File file) {
	// if(tarArchiveOutputStream==null){
	// logger.severe(source.getName()+" not found.");
	// return;
	// }
	// if (file.isFile()) {
	// append(tarArchiveOutputStream,file);
	// }else if (file.isDirectory()) {
	// File[] files=file.listFiles();
	// if (files != null && files.length > 0) {
	// for (File f : files) {
	// action(f);
	// }
	// }
	// }
	// }
	//
	// private void append(TarArchiveOutputStream tarArchiveOutputStream,File
	// file){
	// InputStream is=null;
	// try {
	// is = new BufferedInputStream(new FileInputStream(file));
	// TarArchiveEntry entry = new TarArchiveEntry(file);
	// entry.setSize(file.length());
	// entry.setName(file.getAbsolutePath().substring(source.getAbsolutePath().length()+1));
	// tarArchiveOutputStream.putArchiveEntry(entry);
	// IOUtils.copy(is,tarArchiveOutputStream);
	// tarArchiveOutputStream.flush();
	// tarArchiveOutputStream.closeArchiveEntry();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally {
	// IOUtils.closeQuietly(is);
	// if(deleteSource){
	// if(!file.delete()){
	// logger.warning("Delete source file "+file.getName()+" failed.");
	// }
	// }
	// }
	// }
	//
	// public static void main(String[] args) {
	// TarBuilder tarBuilder = new TarBuilder("D:\\test", "d:\\",
	// "test.tar",false);
	// tarBuilder.build();
	// }
}
