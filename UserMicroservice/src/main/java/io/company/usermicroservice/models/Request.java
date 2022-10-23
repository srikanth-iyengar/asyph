package io.company.usermicroservice.models;


public abstract class Request {
	protected String code;
	protected Language compiler;
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return this.code;
	}

	public void setCompiler(String compiler) {
		this.compiler = Language.valueOf(compiler);
	}

	public Language getCompiler() {
		return this.compiler;
	}
}
