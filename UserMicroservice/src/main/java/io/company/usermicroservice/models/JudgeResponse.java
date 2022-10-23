package io.company.usermicroservice.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class JudgeResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String token;
	private Verdict verdict;
	private String note;
	private Double executionTime;

	public void setExecutionTime(Double executionTime) {
		this.executionTime = executionTime;
	}

	public Double getExecutionTime() {
		return this.executionTime;
	}

	public Verdict getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = Verdict.valueOf(verdict);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "RunnerResponse [token=" + token + ", verdict=" + verdict + "]";
	}

}
