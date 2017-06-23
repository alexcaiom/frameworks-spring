//package br.com.waiso.autenticacao.web;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//
//import br.com.waiso.framework.view.Servlet;
//
///**
// * Servlet implementation class UploadArquivos
// */
//@WebServlet("/UploadArquivos")
//public class UploadArquivos extends Servlet {
//	private static final long serialVersionUID = 1L;
//	
//	private boolean isMultiPart;
//	private String caminhoArquivo;
//	private int maxFileSize = 50 * 1024;
//	private int maxMemSize = 4* 1024;
//	private File arquivo;
//       
//    /**
//     * @see Servlet#Servlet()
//     */
//    public UploadArquivos() {
//        super();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
//		processar(requisicao, resposta);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
//		processar(requisicao, resposta);
//	}
//
//	private void processar(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
//		isMultiPart = ServletFileUpload.isMultipartContent(requisicao);
//		resposta.setContentType("text/html");
//		
//		if (!isMultiPart) {
//			getWriter(null, resposta).println("<html>");
//			getWriter(null, resposta).println("<head>");
//			getWriter(null, resposta).println("<title>Servlet upload</title>");  
//			getWriter(null, resposta).println("</head>");
//			getWriter(null, resposta).println("<body>");
//			getWriter(null, resposta).println("<p>No file uploaded</p>"); 
//			getWriter(null, resposta).println("</body>");
//			getWriter(null, resposta).println("</html>");
//			return;
//		}
//		
//		
//		String caminhoArquivoTemp = getServletContext().getRealPath("/temp/");
//		if (!new File(caminhoArquivoTemp).exists()) {
//			new File(caminhoArquivoTemp).mkdirs();
//		}
//		DiskFileItemFactory fabrica = new DiskFileItemFactory(maxMemSize, new File(caminhoArquivoTemp));
//		
//		ServletFileUpload upload = new ServletFileUpload(fabrica);
//		try{
//			List<FileItem> arquivos = upload.parseRequest(requisicao);
//			Iterator<FileItem> i = arquivos.iterator();
//			
//			
//			getWriter(null, resposta).println("<html>");
//			getWriter(null, resposta).println("<head>");
//			getWriter(null, resposta).println("<title>Servlet upload</title>");  
//			getWriter(null, resposta).println("</head>");
//			getWriter(null, resposta).println("<body>");
//			
//			caminhoArquivo = getServletContext().getRealPath("/storage/");
//			if (!new File(caminhoArquivo).exists()) {
//				new File(caminhoArquivo).mkdirs();
//			}
//			caminhoArquivo = caminhoArquivo + "/";
//			
//			while (i.hasNext()) {
//				FileItem item = i.next();
//				if (!item.isFormField()) {
//					String nomeCampo = item.getFieldName();
//					String nomeArquivo = item.getName();
//					String tipoConteudo = item.getContentType();
//					boolean estaEmMemoria = item.isInMemory();
//					long tamanhoEmBytes = item.getSize();
//					
//					if (nomeArquivo.lastIndexOf("/") >= 0) {
//						arquivo = new File(caminhoArquivo + nomeArquivo.substring(nomeArquivo.lastIndexOf("\\")));
//					} else {
//						arquivo = new File(caminhoArquivo + nomeArquivo.substring(nomeArquivo.lastIndexOf("\\")+1));
//					}
//					
//					item.write(arquivo);
//					getWriter(null, resposta).println("Nome do arquivo: " + nomeArquivo + "<br/>");
//				} else {
//					getWriter(null, resposta).println(item.getString() + "<br/>");
//				}
//			}
//		} catch (Exception e) {
//			getWriter(null, resposta).println("Erro: "+e.getMessage());
//		}
//		getWriter(null, resposta).println("</body>");
//		getWriter(null, resposta).println("</html>");
//	}
//
//}
