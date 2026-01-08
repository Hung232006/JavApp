// BẮT BUỘC gắn vào window để onclick gọi được
window.enableEdit = function () {
    document.querySelectorAll('.view')
        .forEach(e => e.classList.add('hidden'));

    document.querySelectorAll('.editable')
        .forEach(e => e.classList.remove('hidden'));

    document.getElementById('editBtn')?.classList.add('hidden');
    document.getElementById('saveBtn')?.classList.remove('hidden');
};

window.saveMember = function () {
    const data = {};

    document.querySelectorAll('.editable').forEach(input => {
        data[input.name] = input.value;
    });

    const memberID = document.getElementById('memberID').value;

    fetch('/member/' + memberID, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error("Lưu thất bại");
        return res.json();
    })
    .then(() => {
        alert("✔ Cập nhật thành công");
        location.reload();
    })
    .catch(err => alert(err.message));
};
