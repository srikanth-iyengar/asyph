package io.srikanth.onlinejudge.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("requests")
public class JudgeResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String token;
	private Verdict verdict;
	private String note;

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

	@TimeToLive
	public long timeToLive = 600;

	@Override
	public String toString() {
		return "RunnerResponse [token=" + token + ", verdict=" + verdict + "]";
	}

}
