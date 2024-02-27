package com.ai.rag;


import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RagaiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RagaiappApplication.class, args);
	}

	@Bean
	ApplicationRunner demo(
		VectorStore vectorStore,
		@Value("file://D:/Spring microservice/llm-rag-with-spring-ai-main/service/medicaid-wa-faqs.pdf") Resource pdf){
		return args -> {
			//template.update("delete from vector_store");
			var config = PdfDocumentReaderConfig
					.builder()
					.withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder()
							.withNumberOfBottomTextLinesToDelete(3)
							.build())
					.build();

			var pdfReader = new PagePdfDocumentReader(pdf,config);

			var textSplitter = new TokenTextSplitter();
			var docs = textSplitter.apply(pdfReader.get());
			vectorStore.accept(docs);

		};
	}

}
