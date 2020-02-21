package app.example.models;

public class IsPyramidResponse {
    private boolean isPyramid;

    public IsPyramidResponse(Boolean isPyramid) {
        this.isPyramid = isPyramid;
    }

    public Boolean getIsPyramid() {
        return isPyramid;
    }

    public void setIsPyramid(Boolean isPyramid) {
        this.isPyramid = isPyramid;
    }

}
