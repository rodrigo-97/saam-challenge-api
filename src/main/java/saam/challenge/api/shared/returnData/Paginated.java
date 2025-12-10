package saam.challenge.api.shared.returnData;

import java.util.List;

public record Paginated<T>(List<T> data, PaginationInfo pagination) {

    public static <T> Paginated<T> of(List<T> data, long page, long perPage, long count) {
        long totalPages = (long) Math.ceil((double) count / perPage);
        Long previousPage = (page > 1) ? page - 1 : null;
        Long nextPage = (page < totalPages) ? page + 1 : null;

        PaginationInfo paginationInfo = new PaginationInfo(count, totalPages, previousPage, nextPage, page);
        return new Paginated<>(data, paginationInfo);
    }

    public record PaginationInfo(long count, long totalPages, Long previousPage, Long nextPage, long currentPage) {
    }
}
