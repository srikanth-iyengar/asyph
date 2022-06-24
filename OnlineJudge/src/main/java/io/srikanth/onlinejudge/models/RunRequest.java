package io.srikanth.onlinejudge.models;

public class RunRequest {
	private String code;
	private Language compiler;
	private String input;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Language getCompiler() {
		return compiler;
	}

	public void setCompiler(Language compiler) {
		this.compiler = compiler;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
