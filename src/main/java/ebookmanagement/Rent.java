package ebookmanagement;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Rent_table")
public class Rent {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long bookId;
    private String bookName;
    private Long rentalFee;
    private String status;
    private Date rentedDate;
    private Date returnedDate;
    private Date canceledDate;

    @PrePersist
    public void onPrePersist() {
        if (null == this.status) {
            this.status = "RENTED";
        }
    }

    @PostPersist
    public void onPostPersist() {
        if("RENTED".equals(this.status)) {
            Rented rented = new Rented();
            BeanUtils.copyProperties(this, rented);
            rented.publish();

            //Following code causes dependency to external APIs
            // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
            ebookmanagement.external.Payment payment = new ebookmanagement.external.Payment();
            payment.setRentId(this.id);
            payment.setUserId(this.userId);
            payment.setBookId(this.bookId);
            payment.setRentalFee(this.rentalFee);

            EbookmgmtRentApplication.applicationContext.getBean(ebookmanagement.external.PaymentService.class)
                    .payment(payment);
        }
    }

    @PostUpdate
    public void onPostUpdate(){
        if("RETURNED".equals(this.status)) {
            Returned returned = new Returned();
            BeanUtils.copyProperties(this, returned);
            returned.publishAfterCommit();
        } else if("CANCELED".equals(this.status)) {
            Canceled canceled = new Canceled();
            BeanUtils.copyProperties(this, canceled);
            canceled.publishAfterCommit();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public Long getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Long rentalFee) {
        this.rentalFee = rentalFee;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }
    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
    public Date getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(Date canceledDate) {
        this.canceledDate = canceledDate;
    }




}
