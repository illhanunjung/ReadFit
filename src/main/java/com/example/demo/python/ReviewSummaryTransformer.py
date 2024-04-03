from transformers import pipeline
import torch
print(torch.__version__)

summarizer = pipeline("summarization", model="facebook/bart-large-cnn")

text = """여기에 요약하고 싶은 긴 텍스트를 넣습니다. 예를 들어, 뉴스 기사, 연구 논문, 긴 문서 등이 있을 수 있습니다. 
이 모델은 입력된 텍스트를 요약하여 핵심 내용을 간략하게 제시해 줄 것입니다."""

summary = summarizer(text, max_length=130, min_length=30, do_sample=False)
print(summary[0]['summary_text'])


from transformers import PreTrainedTokenizerFast, BartForConditionalGeneration

tokenizer = PreTrainedTokenizerFast.from_pretrained("gogamza/kobart-base-v2")
model = BartForConditionalGeneration.from_pretrained("gogamza/kobart-base-v2")

text = "여기에 요약하고 싶은 한국어 텍스트를 입력합니다. 예를 들어, 긴 뉴스 기사나 보고서 등이 있을 수 있습니다. 이 텍스트는 모델에 의해 요약될 것입니다."

inputs = tokenizer([text], return_tensors="pt", max_length=1024, truncation=True)
summary_ids = model.generate(inputs["input_ids"], num_beams=4, max_length=200, early_stopping=True)
summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

print(summary)


from transformers import AutoTokenizer, AutoModelForSeq2SeqLM, pipeline

# 모델과 토크나이저 로드
tokenizer = AutoTokenizer.from_pretrained("hyunwoongko/kobart")
model = AutoModelForSeq2SeqLM.from_pretrained("hyunwoongko/kobart")

# 파이프라인 설정 (텍스트 요약)
summarizer = pipeline("summarization", model=model, tokenizer=tokenizer)

# 요약할 텍스트
text = """
한국의 경제 성장률이 전년 대비 2.5% 증가했다고 한국은행이 발표했습니다. 이는 최근 몇 년간 경제 성장세가 둔화된 것에 비해 상당한 개선이며,
내수와 수출 모두에서 긍정적인 성장세를 보이고 있다고 합니다. 특히 반도체 산업과 자동차 산업에서의 수출 호조가 경제 성장에 크게 기여하고 있습니다.
"""

# 텍스트 요약 실행
summary = summarizer(text, max_length=45, min_length=5, do_sample=False)

print(summary[0]['summary_text'])
