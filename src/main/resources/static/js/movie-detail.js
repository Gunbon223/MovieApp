const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// render review
const formatDate = dateStr => {
    const date = new Date(dateStr);
    const day = `0${date.getDate()}`.slice(-2); // 01 -> 01, 015 -> 15
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}
const reviewListEl = document.querySelector(".review-list");
const renderReview = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3 text-white">
                <div class="rating-avatar">
                    <img src="${review.user.avatar}" alt="${review.user.name}" style="width: 120px">
                </div>
                <div class="rating-info ms-3">
                    <div class="d-flex align-items-center">
                        <p class="rating-name mb-0">
                            <strong>${review.user.name}</strong>
                        </p>
                        <p class="rating-time mb-0 ms-2">${formatDate(review.createdAt)}</p>
                         <div>
<div>
                            <button class="btn btn-primary ms-4 btn-edit-review"
                                    data-review-id="${review.id}"
                                    data-review-content="${review.content}"
                                    data-review-rating="${review.rating}">Change</button>   
                            <button class="btn btn-primary ms-2 btn-delete-review"
                                    data-review-id="${review.id}">Delete</button>                                                            
                        </div>                            
                        </div>
                    </div>
                    <div class="rating-star">
                        <p class="mb-0 fw-bold">
                            <span class="rating-icon"><i class="fa fa-star"></i></span>
                            <span>${review.rating}/10 Tuyệt vời</span>
                        </p>

                    </div>
                    <p class="rating-content mt-1 mb-0 ">${review.content}</p>
                </div>
            </div>
        `
    })

    reviewListEl.innerHTML = html;
    attachEventListeners();
}

// Tạo review
const reviewContentEl = document.getElementById("review-content");
const modalReviewEl = document.getElementById("modal-review");
const formReviewEl2 = document.getElementById("form-review");
const myModalReviewEl = new bootstrap.Modal(modalReviewEl, {
    keyboard: false
})

modalReviewEl.addEventListener('hidden.bs.modal', event => {
    console.log("Su kien dong modal")
    currentRating = 0;
    reviewContentEl.value = "";
    ratingValue.textContent = "";
    resetStars();
})

document.addEventListener("DOMContentLoaded", function() {
    const formReviewEl = document.getElementById("form-review");
    if(formReviewEl) {

        formReviewEl.addEventListener("submit", async (e) => {
            e.preventDefault();

            // TODO: Validate các thông tin (sử dụng thư jQuery Validation)
            if (currentRating === 0) {
                alert("Vui lòng chọn số sao");
                return;
            }

            if (reviewContentEl.value.trim() === "") {
                alert("Nội dung đánh giá không được để trống");
                return;
            }

            const data = {
                content: reviewContentEl.value,
                rating: currentRating,
                movieId: movie.id
            }

            // Gọi API
            try {
                let res = await axios.post("/api/reviews", data);
                reviews.unshift(res.data);
                renderReview(reviews);

                myModalReviewEl.hide();

                // reset
            } catch (e) {
                console.log(e)
            }
        })
    } else {
        console.error("Element with id 'form-review' was not found");
    }
});

document.addEventListener("DOMContentLoaded", function() {
    attachEventListeners();
});
const attachEventListeners = () => {
    const changeButtons = document.querySelectorAll(".btn-edit-review");
    const deleteButtons = document.querySelectorAll(".btn-delete-review");

    const modalCreateBtn = document.getElementById("btn-create-review");
    const modalTitle = document.getElementById("staticBackdropLabel");

    modalCreateBtn.innerHTML = "Sửa";
    modalTitle.innerHTML = "Sửa đánh giá";
    changeButtons.forEach((button) => {
        button.addEventListener("click", function() {
            // Get the review data from the button's data attributes
            const reviewId = this.getAttribute("data-review-id");
            const reviewContent = this.getAttribute("data-review-content");
            const reviewRating = this.getAttribute("data-review-rating");

            // Set the current review data
            reviewContentEl.value = reviewContent;
            // Set the current rating
            currentRating = parseInt(reviewRating);
            highlightStars(currentRating);

            myModalReviewEl.show();

            // Add a new event listener for form submission
            formReviewEl2.addEventListener("submit", async (e) => {
                e.preventDefault();

                const data = {
                    content: reviewContentEl.value,
                    rating: currentRating,
                    movieId: movie.id
                }

                // Update the review
                try {
                    let res = await axios.put("/api/reviews/" + reviewId, data);
                    const reviewIndex = reviews.findIndex(review => review.id === reviewId);
                    reviews[reviewIndex] = res.data;
                    renderReview(reviews);

                    myModalReviewEl.hide();
                } catch (e) {
                    console.log(e)
                }
            });
        });
    });

    deleteButtons.forEach((button) => {
        button.addEventListener("click", async function () {
            const reviewId = Number(this.getAttribute("data-review-id"));
            // Show alert
            const confirmDelete = confirm("Xoá đánh giá?");
            if (confirmDelete) {
                try {
                    let res = await axios.delete("/api/reviews/" + reviewId);
                    const reviewIndex = reviews.findIndex(review => review.id === reviewId);
                    if (reviewIndex !== -1) {
                        reviews.splice(reviewIndex, 1);
                        renderReview(reviews);
                    }
                } catch (e) {
                    console.log(e)
                }
            }
        });
    });
}