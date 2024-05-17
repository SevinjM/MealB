package payloadBuilder;

public class GiftExpenseRequestBuilder {

	private String name;
	private String amount;
	private String expenseDateTime;
	private String expenseType;
	private String giftRecipient;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getExpenseDateTime() {
		return expenseDateTime;
	}
	public void setExpenseDateTime(String expenseDateTime) {
		this.expenseDateTime = expenseDateTime;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getGiftRecipient() {
		return giftRecipient;
	}
	public void setGiftRecipient(String giftRecipient) {
		this.giftRecipient = giftRecipient;
	}
	
}
